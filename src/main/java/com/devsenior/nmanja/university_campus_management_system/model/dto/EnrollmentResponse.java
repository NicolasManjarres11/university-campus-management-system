package com.devsenior.nmanja.university_campus_management_system.model.dto;

import java.time.LocalDate;

import com.devsenior.nmanja.university_campus_management_system.model.summaries.CourseSummary;
import com.devsenior.nmanja.university_campus_management_system.model.summaries.StudentSummary;

public record EnrollmentResponse(

Long id,

StudentSummary student, //SubDTO para los datos del estudiante

CourseSummary course, //SubDto para los datos del curso

LocalDate inscriptionDate,

String status


) {


    
}
