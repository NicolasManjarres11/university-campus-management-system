package com.devsenior.nmanja.university_campus_management_system.model.summaries;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProfessorSummary(

    //Se crea este subDTO para los response en Cursos, con el fin de 
    //Exponer la mínima información posible y no entregar "respuestas pesadas"


    @JsonProperty(value = "ID")
    Long id,

    @JsonProperty(value = "Nombre")
    String name,

    @JsonProperty(value = "Correo")
    String email

) {
    
}
