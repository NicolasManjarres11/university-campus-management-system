package com.devsenior.nmanja.university_campus_management_system.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ProfessorRequest(
    @Schema(
        description = "Nombre completo del profesor",
        example = "Dr. Juan Carlos Pérez González",
        required = true
    )
    @NotBlank(message = "El nombre es obligatorio")
    String name,

    @Schema(
        description = "Departamento académico al que pertenece el profesor",
        example = "Matemáticas",
        required = true
    )
    @NotBlank(message = "El departamento es obligatorio")
    String department,

    @Schema(
        description = "Correo electrónico institucional del profesor",
        example = "juan.perez@devsenior.edu.co",
        required = true,
        format = "email"
    )
    @Pattern( 
        regexp = "^[a-zA-Z0-9._%+-]+@devsenior\\.edu\\.co$",
        message = "El correo no tiene un formato valido 'usuario@devsenior.edu.co'")
    @NotBlank(message = "El correo electrónico es obligatorio")
    String email
) {
}
