package com.devsenior.nmanja.university_campus_management_system.exceptions;

public class StatusNotValidException extends IllegalArgumentException{
    
    public StatusNotValidException(String status){
        super("El estado '"+status+"' NO es válido.");
    }

    public StatusNotValidException(Long id){
        super("No se puede cancelar la inscripción con ID "+id+" porque este ya fue completado.");
    }

}
