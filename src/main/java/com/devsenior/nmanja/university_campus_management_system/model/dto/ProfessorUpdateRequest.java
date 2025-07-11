package com.devsenior.nmanja.university_campus_management_system.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

//Clase para actualizar los datos del profesor, se crea con el fin de no colocar obligatoriamente todos los datos
//o para evitar conflictos de datos duplicados cuando no se necesite cambiar el correo u otro dato
public record ProfessorUpdateRequest(

        @Schema(
            description = "Nuevo nombre completo del profesor (opcional para actualización)", 
            example = "Dr. Juan Carlos Pérez González")

        String name,

        @Schema(
            description = "Nuevo departamento académico del profesor (opcional para actualización)", 
            example = "Matemáticas") 
        
        String department,

        @Schema(
            description = "Nuevo correo electrónico institucional del profesor (opcional para actualización)", 
            example = "juan.perez@devsenior.edu.co", 
            format = "email")

        @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@devsenior\\.edu\\.co$",
         message = "El correo no tiene un formato valido 'usuario@devsenior.edu.co'"
         ) 
         
         String email

    ){

}
