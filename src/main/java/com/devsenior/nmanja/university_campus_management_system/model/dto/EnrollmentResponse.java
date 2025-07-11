package com.devsenior.nmanja.university_campus_management_system.model.dto;

import java.time.LocalDate;

import com.devsenior.nmanja.university_campus_management_system.model.summaries.CourseSummary;
import com.devsenior.nmanja.university_campus_management_system.model.summaries.StudentSummary;

import io.swagger.v3.oas.annotations.media.Schema;

public record EnrollmentResponse(
    @Schema(
        description = "Identificador único de la inscripción",
        example = "1"
    )
    Long id,

    @Schema(
        description = "Información resumida del estudiante inscrito en el curso",
        example = "{\"id\": 1, \"name\": \"María García\", \"email\": \"maria.garcia@devsenior.edu.co\"}"
    )
    StudentSummary student,

    @Schema(
        description = "Información resumida del curso en el que se inscribió el estudiante",
        example = "{\"id\": 1, \"courseName\": \"Matemáticas Avanzadas\", \"courseCode\": \"MAT-201\"}"
    )
    CourseSummary course,

    @Schema(
        description = "Fecha en que se realizó la inscripción del estudiante en el curso",
        example = "2024-01-15",
        format = "date"
    )
    LocalDate inscriptionDate,

    @Schema(
        description = "Estado actual de la inscripción del estudiante en el curso",
        example = "ACTIVO",
        allowableValues = {"ACTIVO", "COMPLETADO", "RETIRADO"}
    )
    String status
) {
}
