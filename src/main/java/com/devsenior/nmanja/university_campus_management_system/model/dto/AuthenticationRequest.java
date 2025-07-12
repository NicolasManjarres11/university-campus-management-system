package com.devsenior.nmanja.university_campus_management_system.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;


public record AuthenticationRequest(
    @Schema(
        description = "Nombre de usuario único para autenticación en el sistema",
        example = "maria.garcia",
        required = true
    )
    @NotBlank(message = "El 'username' es un campo obligatorio")
    String username,

    @Schema(
        description = "Contraseña del usuario para autenticación",
        example = "password123",
        required = true,
        writeOnly = true
    )
    @NotBlank(message = "La contraseña es un campo obligatorio")
    String password
) {
}
