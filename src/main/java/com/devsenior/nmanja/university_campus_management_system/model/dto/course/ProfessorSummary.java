package com.devsenior.nmanja.university_campus_management_system.model.dto.course;

public record ProfessorSummary(

    //Se crea este subDTO para los response en Cursos, con el fin de 
    //Exponer la mínima información posible y no entregar "respuestas pesadas"

    Long id,
    String name,
    String email

) {
    
}
