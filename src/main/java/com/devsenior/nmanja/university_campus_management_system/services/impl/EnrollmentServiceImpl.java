package com.devsenior.nmanja.university_campus_management_system.services.impl;

import java.time.LocalDate;
import java.util.List;

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
public class EnrollmentServiceImpl implements EnrollmentService{

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
    public EnrollmentResponse createEnrollment(EnrollmentRequest enrollment) {
        
        var entity = enrollmentMapper.toEntity(enrollment);

        var student = studentRepository.findById(enrollment.studentId())
                        .orElseThrow(() -> new EntityNotFoundException(enrollment.studentId(), "estudiante"));

        var course = courseRepository.findById(enrollment.courseId())
                        .orElseThrow(() -> new EntityNotFoundException(enrollment.courseId(), "curso"));

        if (enrollmentRepository.existsByStudentIdAndCourseId(enrollment.studentId(), enrollment.courseId())) {
                            throw new StudentAlreadyEnrolled(enrollment.studentId());
        }

        courseService.incrementStudentsInCourse(course.getId());

        entity.setStudent(student);
        entity.setCourse(course);
        entity.setInscriptionDate(LocalDate.now());
        entity.setStatus(EnrollmentStatus.ACTIVO);

        enrollmentRepository.save(entity);

        return enrollmentMapper.toResponse(entity);
    }

    @Transactional
    @Override
    public EnrollmentResponse updateEnrollment(Long id, EnrollmentUpdateRequest enrollment) {

        var entityOptional = enrollmentRepository.findById(id).
                            orElseThrow(() -> new EntityNotFoundException(id, "inscripción"));
        //Guardamos el nuevo estado
        EnrollmentStatus newStatus;

        try {
            newStatus = EnrollmentStatus.valueOf(enrollment.status());
        } catch (IllegalArgumentException e) {
            throw new StatusNotValidException(enrollment.status());
        }

        //El viejo estado lo obtenemos para validar
        var oldStatus = entityOptional.getStatus();


        //Si el usuario actualiza con el estado ACTIVO a un curso que ya está ACTIVO, lo mismo con los demás estados

        if(newStatus == oldStatus){
            return enrollmentMapper.toResponse(entityOptional);
        }


        if(oldStatus == EnrollmentStatus.ACTIVO && newStatus != EnrollmentStatus.ACTIVO){
            courseService.decrementStudentsInCourse(entityOptional.getCourse().getId());
        } else if (oldStatus != EnrollmentStatus.ACTIVO && newStatus == EnrollmentStatus.ACTIVO){
            courseService.incrementStudentsInCourse(entityOptional.getCourse().getId());
        }

        entityOptional.setStatus(newStatus);
        
        var updateEntity = enrollmentRepository.save(entityOptional);



        return enrollmentMapper.toResponse(updateEntity);
    }


    @Transactional
    @Override
    public EnrollmentResponse cancelEnrollment(Long id) {
        
        var entityOptional = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "inscripción"));

        var oldStatus = entityOptional.getStatus();

        if(oldStatus == EnrollmentStatus.RETIRADO){
            return enrollmentMapper.toResponse(entityOptional);
        }

        if(oldStatus == EnrollmentStatus.COMPLETADO){
            throw new StatusNotValidException(id);
        }

        if(oldStatus == EnrollmentStatus.ACTIVO){
            courseService.decrementStudentsInCourse(entityOptional.getCourse().getId());
        } 

        entityOptional.setStatus(EnrollmentStatus.RETIRADO);

        var updatedEntity = enrollmentRepository.save(entityOptional);
        
        return enrollmentMapper.toResponse(updatedEntity);
    }
    
}
