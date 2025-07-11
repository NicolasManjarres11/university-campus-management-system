package com.devsenior.nmanja.university_campus_management_system.model.dto;

import java.util.List;

import com.devsenior.nmanja.university_campus_management_system.model.summaries.EnrollmentSummary;

import io.swagger.v3.oas.annotations.media.Schema;

public record StudentResponse(
    @Schema(
        description = "Identificador único del estudiante",
        example = "1"
    )
    Long id,

    @Schema(
        description = "Nombre completo del estudiante",
        example = "María Alejandra García López"
    )
    String name,

    @Schema(
        description = "Correo electrónico institucional del estudiante",
        example = "maria.garcia@devsenior.edu.co",
        format = "email"
    )
    String email,

    @Schema(
        description = "Número de identificación académica único del estudiante",
        example = "2024001"
    )
    String studentNumber,

    @Schema(
        description = "Lista de inscripciones del estudiante en diferentes cursos",
        example = "[{\"id\": 1, \"course\": {\"courseName\": \"Matemáticas Avanzadas\"}, \"status\": \"ACTIVO\"}]"
    )
    List<EnrollmentSummary> enrollments
) {
}
