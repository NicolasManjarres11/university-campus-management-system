package com.devsenior.nmanja.university_campus_management_system.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Long id, String entity){
        super(entity.equalsIgnoreCase("inscripción") ?
        "No se encontró la "+entity+" con el ID: "+id : "No se encontró el "+entity+" con el ID: "+id);

        
    }

    public EntityNotFoundException(String msg){
        super(msg);

        
    }
    
}
