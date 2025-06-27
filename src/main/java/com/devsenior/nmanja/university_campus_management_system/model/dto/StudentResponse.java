package com.devsenior.nmanja.university_campus_management_system.model.dto;

import java.util.List;


public record StudentResponse(

    Long id,

    String name,

    String email,

    String studentNumber,

    List<EnrollmentResponse> enrollments

) {
    
}
