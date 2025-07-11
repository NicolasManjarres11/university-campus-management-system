package com.devsenior.nmanja.university_campus_management_system.model.dto;

import java.util.List;

import com.devsenior.nmanja.university_campus_management_system.model.summaries.CourseSummary;
import io.swagger.v3.oas.annotations.media.Schema;

public record ProfessorResponse(
    @Schema(
        description = "Identificador único del profesor",
        example = "1"
    )
    Long id,

    @Schema(
        description = "Nombre completo del profesor",
        example = "Dr. Juan Carlos Pérez González"
    )
    String name,

    @Schema(
        description = "Departamento académico al que pertenece el profesor",
        example = "Matemáticas"
    )
    String department,

    @Schema(
        description = "Correo electrónico institucional del profesor",
        example = "juan.perez@devsenior.edu.co",
        format = "email"
    )
    String email,

    @Schema(
        description = "Lista de cursos que dicta el profesor",
        example = "[{\"id\": 1, \"courseName\": \"Matemáticas Avanzadas\", \"courseCode\": \"MAT-201\"}]"
    )
    List<CourseSummary> courses
) {
}
