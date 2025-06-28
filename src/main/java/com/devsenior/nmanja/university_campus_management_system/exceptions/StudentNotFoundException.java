package com.devsenior.nmanja.university_campus_management_system.exceptions;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(Long id){
        super("No se encontró el estudiante con el ID: "+id);
    }
    
}
