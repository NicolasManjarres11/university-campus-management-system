package com.devsenior.nmanja.university_campus_management_system.model.dto;

import jakarta.validation.constraints.Pattern;

//Clase para actualizar los datos de estudiante, se crea con el fin de no colocar obligatoriamente todos los datos
//o para evitar conflictos de datos duplicados cuando no se necesite cambiar el correo o el número del estudiante
public record StudentUpdateRequest(


    String name,

    @Pattern(
        regexp = "^[a-zA-Z0-9._%+-]+@devsenior\\.edu\\.co$",
        message = "El correo no tiene un formato valido 'usuario@devsenior.edu.co'"
        )

    String email,


    String studentNumber


) {
    
}
