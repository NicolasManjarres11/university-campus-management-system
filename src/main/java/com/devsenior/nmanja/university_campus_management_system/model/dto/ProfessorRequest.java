package com.devsenior.nmanja.university_campus_management_system.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessorRequest(

    @NotBlank(message = "El nombre es obligatorio")
    String name,

    @NotBlank(message = "El departamento es obligatorio")
    String department,

    @Email(message = "El correo no tiene un formato válido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    String email

    

) {
    
}
