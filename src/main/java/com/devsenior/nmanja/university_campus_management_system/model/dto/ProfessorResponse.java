package com.devsenior.nmanja.university_campus_management_system.model.dto;

import java.util.List;

import com.devsenior.nmanja.university_campus_management_system.model.summaries.CourseSummary;

public record ProfessorResponse(

    Long id,

    String name,

    String department,

    String email,

    List<CourseSummary> courses

) {
    
    

}
