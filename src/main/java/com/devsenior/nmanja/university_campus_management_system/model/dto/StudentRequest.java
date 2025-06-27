package com.devsenior.nmanja.university_campus_management_system.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentRequest(

    @NotBlank(message = "El nombre del estudiante es obligatorio")
    String name,

    @Email(message = "El correo no tiene un formato valido")
    @NotBlank(message = "El correo del estudiante es obligatorio")
    String email,

    @NotBlank(message = "El número del estudiante es obligatorio")
    String studentNumber


) {
    
}
