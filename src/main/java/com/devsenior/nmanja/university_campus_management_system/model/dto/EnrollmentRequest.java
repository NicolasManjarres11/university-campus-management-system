package com.devsenior.nmanja.university_campus_management_system.model.dto;

import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public record EnrollmentRequest(
    @Schema(
        description = "Identificador único del estudiante que se inscribirá en el curso",
        example = "1",
        required = true
    )
    @NotNull(message = "El ID del estudiante es obligatorio")
    Long studentId,

    @Schema(
        description = "Identificador único del curso en el que se inscribirá el estudiante",
        example = "1",
        required = true
    )
    @NotNull(message = "El ID del curso es obligatorio")
    Long courseId
) {
}
