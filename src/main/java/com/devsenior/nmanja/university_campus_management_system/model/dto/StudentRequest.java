package com.devsenior.nmanja.university_campus_management_system.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;

public record StudentRequest(
    @Schema(
        description = "Nombre completo del estudiante",
        example = "María Alejandra García López",
        required = true
    )
    @NotBlank(message = "El nombre del estudiante es obligatorio")
    String name,

    @Schema(
        description = "Correo electrónico institucional del estudiante",
        example = "maria.garcia@devsenior.edu.co",
        required = true,
        format = "email"
    )
    @Pattern(
        regexp = "^[a-zA-Z0-9._%+-]+@devsenior\\.edu\\.co$",
        message = "El correo no tiene un formato valido 'usuario@devsenior.edu.co'"
    )
    @NotBlank(message = "El correo del estudiante es obligatorio.")
    String email,

    @Schema(
        description = "Número de identificación académica único del estudiante",
        example = "2024001",
        required = true
    )
    @NotBlank(message = "El número del estudiante es obligatorio.")
    String studentNumber,

    @Schema(
        description = "Nombre de usuario único para autenticación en el sistema",
        example = "maria.garcia",
        required = true
    )
    @NotBlank(message = "El 'username' es obligatorio.")
    String username,

    @Schema(
        description = "Contraseña para la cuenta del estudiante",
        example = "password123",
        required = true,
        writeOnly = true
    )
    @NotBlank(message = "La contraseña es obligatoria.")
    String password
) {
}
