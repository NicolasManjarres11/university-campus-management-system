package com.devsenior.nmanja.university_campus_management_system.services;

import java.util.List;

import com.devsenior.nmanja.university_campus_management_system.model.dto.EnrollmentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.EnrollmentResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.EnrollmentUpdateRequest;

public interface EnrollmentService {
    
    List<EnrollmentResponse> getAllEnrollments();

    EnrollmentResponse getEnrollmentById(Long id);

    EnrollmentResponse createEnrollment(EnrollmentRequest enrollment, String username, List<String> roles);

    EnrollmentResponse updateEnrollment(Long id, EnrollmentUpdateRequest enrollment);

    EnrollmentResponse cancelEnrollment(Long id, String username, List<String> roles);

}
