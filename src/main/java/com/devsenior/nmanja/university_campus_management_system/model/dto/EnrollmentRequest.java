package com.devsenior.nmanja.university_campus_management_system.model.dto;

import jakarta.validation.constraints.NotNull;

public record EnrollmentRequest(

@NotNull(message = "El ID del estudiante es obligatorio")
Long studentId,

@NotNull(message = "El ID del curso es obligatorio")
Long courseId


) {


    
}
