package com.devsenior.nmanja.university_campus_management_system.exceptions;

public class ProfessorsNotExistException extends RuntimeException{

    public ProfessorsNotExistException(){
        super("No se encontraron profesores en la base de datos.");
    }
    
}
