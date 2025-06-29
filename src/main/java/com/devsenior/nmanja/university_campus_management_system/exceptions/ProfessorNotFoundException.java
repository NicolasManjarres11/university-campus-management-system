package com.devsenior.nmanja.university_campus_management_system.exceptions;

public class ProfessorNotFoundException extends RuntimeException{

    public ProfessorNotFoundException(Long id){
        super("No se encontró el profesor con el ID: "+id);
    }
    
}
