package com.devsenior.nmanja.university_campus_management_system.model.dto;

import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;

//Clase para actualizar los datos de estudiante, se crea con el fin de no colocar obligatoriamente todos los datos
//o para evitar conflictos de datos duplicados cuando no se necesite cambiar el correo o el número del estudiante
public record StudentUpdateRequest(
    @Schema(
        description = "Nuevo nombre completo del estudiante (opcional para actualización)",
        example = "María Alejandra García López"
    )
    String name,

    @Schema(
        description = "Nuevo correo electrónico institucional del estudiante (opcional para actualización)",
        example = "maria.garcia@devsenior.edu.co",
        format = "email"
    )
    @Pattern(
        regexp = "^[a-zA-Z0-9._%+-]+@devsenior\\.edu\\.co$",
        message = "El correo no tiene un formato valido 'usuario@devsenior.edu.co'"
    )
    String email,

    @Schema(
        description = "Nuevo número de identificación académica del estudiante (opcional para actualización)",
        example = "2024001"
    )
    String studentNumber
) {
}
