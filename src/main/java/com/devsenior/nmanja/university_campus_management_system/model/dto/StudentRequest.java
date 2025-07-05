package com.devsenior.nmanja.university_campus_management_system.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record StudentRequest(

    @NotBlank(message = "El nombre del estudiante es obligatorio")
    String name,

    @Pattern(
        regexp = "^[a-zA-Z0-9._%+-]+@devsenior\\.edu\\.co$",
        message = "El correo no tiene un formato valido 'usuario@devsenior.edu.co'"
        )
    @NotBlank(message = "El correo del estudiante es obligatorio.")
    String email,

    @NotBlank(message = "El número del estudiante es obligatorio.")
    String studentNumber,

    @NotBlank(message = "El 'username'  es obligatorio.")
    String username,

    @NotBlank(message = "La contraseña es obligatoria.")
    String password


) {
    
}
