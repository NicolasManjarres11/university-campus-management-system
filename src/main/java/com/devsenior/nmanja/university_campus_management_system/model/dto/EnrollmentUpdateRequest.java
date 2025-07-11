package com.devsenior.nmanja.university_campus_management_system.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public record EnrollmentUpdateRequest(
    @Schema(
        description = "Nuevo estado de la inscripción del estudiante en el curso",
        example = "COMPLETADO",
        allowableValues = {"ACTIVO", "COMPLETADO"},
        required = true
    )
    @Pattern(
        regexp = "^(ACTIVO|COMPLETADO)$", 
        message = "El status debe ser: ACTIVO o COMPLETADO"
    )
    String status
) {
}
