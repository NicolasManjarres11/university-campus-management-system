package com.devsenior.nmanja.university_campus_management_system.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public record CourseUpdateRequest(

    String courseName, 

    @Pattern(
        regexp = "^[A-Z]{3}-\\d{3}$",
        message = "El código debe tener el formato ABC-123"
    )

    String courseCode,


    String description,


    @Min(value = 10, message = "La capacidad máxima debe ser al menos de 10 estudiantes")
    Integer maxStudents,

    @Min(value = 0)
    Integer studentsInCourse,


    Long professorId
) {
    
}
