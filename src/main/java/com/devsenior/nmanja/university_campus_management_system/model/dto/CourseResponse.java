package com.devsenior.nmanja.university_campus_management_system.model.dto;

import com.devsenior.nmanja.university_campus_management_system.model.summaries.ProfessorSummary;

public record CourseResponse(

Long id,

String courseName,

String courseCode,


String description,


Integer maxStudents,


ProfessorSummary professor


) {
    
}
