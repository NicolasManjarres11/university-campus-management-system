package com.devsenior.nmanja.university_campus_management_system.model.dto;

import java.time.LocalDate;

import com.devsenior.nmanja.university_campus_management_system.model.dto.enrollment.CourseSummary;
import com.devsenior.nmanja.university_campus_management_system.model.dto.enrollment.StudentSummary;

public record EnrollmentResponse(

Long id,

StudentSummary student, //Sub

CourseSummary course,

LocalDate inscriptionDate,

String status


) {


    
}
