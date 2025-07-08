package com.devsenior.nmanja.university_campus_management_system.model.dto;

import java.util.List;

import com.devsenior.nmanja.university_campus_management_system.model.summaries.EnrollmentSummary;
import com.devsenior.nmanja.university_campus_management_system.model.summaries.UserSummary;


public record StudentResponse(

    Long id,

    String name,

    String email,

    String studentNumber,

    List<EnrollmentSummary> enrollments

) {
    
}
