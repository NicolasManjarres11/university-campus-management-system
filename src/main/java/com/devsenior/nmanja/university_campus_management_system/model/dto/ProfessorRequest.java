package com.devsenior.nmanja.university_campus_management_system.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ProfessorRequest(

    @NotBlank(message = "El nombre es obligatorio")
    String name,

    @NotBlank(message = "El departamento es obligatorio")
    String department,

    @Pattern( 
        regexp = "^[a-zA-Z0-9._%+-]+@devsenior\\.edu\\.co$",
        message = "El correo no tiene un formato valido 'usuario@devsenior.edu.co'")
    @NotBlank(message = "El correo electrónico es obligatorio")
    String email

    

) {
    
}
