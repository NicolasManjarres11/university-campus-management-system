package com.devsenior.nmanja.university_campus_management_system.model.dto;

import jakarta.validation.constraints.Pattern;

public record EnrollmentUpdateRequest(


    @Pattern(
        regexp = "^(ACTIVO|COMPLETADO)$", 
        message = "El status debe ser: ACTIVO o COMPLETADO")
    String status
) {
    
}
