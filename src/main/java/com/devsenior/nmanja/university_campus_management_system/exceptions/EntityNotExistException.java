package com.devsenior.nmanja.university_campus_management_system.exceptions;

public class EntityNotExistException extends RuntimeException{

    public EntityNotExistException(String entity){
        super("No se encontraron "+entity+" en la base de datos");
    }
    
}
