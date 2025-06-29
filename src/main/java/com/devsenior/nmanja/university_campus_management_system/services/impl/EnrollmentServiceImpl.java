package com.devsenior.nmanja.university_campus_management_system.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.nmanja.university_campus_management_system.exceptions.EntityNotExistException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.EntityNotFoundException;
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

    @Override
    public EnrollmentResponse updateEnrollment(EnrollmentUpdateRequest enrollment) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EnrollmentResponse cancelEnrollment(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
