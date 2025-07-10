package com.devsenior.nmanja.university_campus_management_system.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsenior.nmanja.university_campus_management_system.exceptions.EntityNotExistException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.EntityNotFoundException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.StatusNotValidException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.StudentAlreadyEnrolled;
import com.devsenior.nmanja.university_campus_management_system.mappers.EnrollmentMapper;
import com.devsenior.nmanja.university_campus_management_system.model.dto.EnrollmentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.EnrollmentResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.EnrollmentUpdateRequest;
import com.devsenior.nmanja.university_campus_management_system.model.enums.EnrollmentStatus;
import com.devsenior.nmanja.university_campus_management_system.repositories.CourseRepository;
import com.devsenior.nmanja.university_campus_management_system.repositories.EnrollmentRepository;
import com.devsenior.nmanja.university_campus_management_system.repositories.StudentRepository;
import com.devsenior.nmanja.university_campus_management_system.services.CourseService;
import com.devsenior.nmanja.university_campus_management_system.services.EnrollmentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    private final CourseService courseService;

    @Override
    public List<EnrollmentResponse> getAllEnrollments() {

        var entity = enrollmentRepository.findAll();

        if (entity.isEmpty()) {
            throw new EntityNotExistException("inscripciones");
        }

        var response = entity.stream()
                .map(e -> enrollmentMapper.toResponse(e))
                .toList();

        return response;
    }

    @Override
    public EnrollmentResponse getEnrollmentById(Long id) {
        var entityOptional = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "inscripción"));

        return enrollmentMapper.toResponse(entityOptional);
    }

    @Transactional
    @Override
    public EnrollmentResponse createEnrollment(EnrollmentRequest enrollment, String username, List<String> roles) {


        //Validamos que el estudiante exista

        var student = studentRepository.findById(enrollment.studentId())
                .orElseThrow(() -> new EntityNotFoundException(enrollment.studentId(), "estudiante"));


        //Revisamos si el usuario es ADMIN o no

        if (!roles.contains("ROLE_ADMIN")) {

            //Si no es administrador, se busca el estudiante con este username

            var user = studentRepository.findByUserUsername(username)
                    .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario: " + username));

            //Si el ID del usuario no coincide con el ID relacionado en el request, se prohibe la acción de inscripción

            if (!user.getId().equals(enrollment.studentId())) {

                throw new AuthorizationDeniedException("No tienes permiso para realizar esta operación.");

            }

        }

        //Validamos que el curso exista
        

        var course = courseRepository.findById(enrollment.courseId())
                .orElseThrow(() -> new EntityNotFoundException(enrollment.courseId(), "curso"));

        //Revisamos si el usuario ya está inscrito en el curso

        if (enrollmentRepository.existsByStudentIdAndCourseId(enrollment.studentId(), enrollment.courseId())) {
            throw new StudentAlreadyEnrolled(enrollment.studentId());
        }
        
        //Si pasa todos los filtros, se procede a crear la inscripción

        var entity = enrollmentMapper.toEntity(enrollment);
        entity.setStudent(student);
        entity.setCourse(course);
        entity.setInscriptionDate(LocalDate.now());
        entity.setStatus(EnrollmentStatus.ACTIVO);

       //Se incrementa contador de estudiantes en el curso
        courseService.incrementStudentsInCourse(course.getId());

        //Guardamos la inscripción en la BD
        enrollmentRepository.save(entity);
        return enrollmentMapper.toResponse(entity);

    }

    @Transactional
    @Override
    public EnrollmentResponse updateEnrollment(Long id, EnrollmentUpdateRequest enrollment) {

        var entityOptional = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "inscripción"));
        // Guardamos el nuevo estado
        EnrollmentStatus newStatus;

        try {
            newStatus = EnrollmentStatus.valueOf(enrollment.status());
        } catch (IllegalArgumentException e) {
            throw new StatusNotValidException(enrollment.status());
        }

        // El viejo estado lo obtenemos para validar
        var oldStatus = entityOptional.getStatus();

        // Si el usuario actualiza con el estado ACTIVO a un curso que ya está ACTIVO,
        // lo mismo con los demás estados

        if (newStatus == oldStatus) {
            return enrollmentMapper.toResponse(entityOptional);
        }

        if (oldStatus == EnrollmentStatus.ACTIVO && newStatus != EnrollmentStatus.ACTIVO) {
            courseService.decrementStudentsInCourse(entityOptional.getCourse().getId());
        } else if (oldStatus != EnrollmentStatus.ACTIVO && newStatus == EnrollmentStatus.ACTIVO) {
            courseService.incrementStudentsInCourse(entityOptional.getCourse().getId());
        }

        entityOptional.setStatus(newStatus);

        var updateEntity = enrollmentRepository.save(entityOptional);

        return enrollmentMapper.toResponse(updateEntity);
    }

    @Transactional
    @Override
    public EnrollmentResponse cancelEnrollment(Long id, String username, List<String> roles) {

        var entityOptional = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "inscripción"));


        if(!roles.contains("ROLE_ADMIN")){

            var user = studentRepository.findByUserUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("No se encontró estudiante con el usuario :" + username));

            if (user.getId() != entityOptional.getStudent().getId()) {
                throw new AuthorizationDeniedException("No tienes permisos para realizar esta acción.");
            }

        }

        var oldStatus = entityOptional.getStatus();

        if (oldStatus == EnrollmentStatus.RETIRADO) {
            return enrollmentMapper.toResponse(entityOptional);
        }

        if (oldStatus == EnrollmentStatus.COMPLETADO) {
            throw new StatusNotValidException(id);
        }

        if (oldStatus == EnrollmentStatus.ACTIVO) {
            courseService.decrementStudentsInCourse(entityOptional.getCourse().getId());
        }

        entityOptional.setStatus(EnrollmentStatus.RETIRADO);

        var updatedEntity = enrollmentRepository.save(entityOptional);

        return enrollmentMapper.toResponse(updatedEntity);
    }

}
