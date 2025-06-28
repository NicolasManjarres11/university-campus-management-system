package com.devsenior.nmanja.university_campus_management_system.exceptions;

public class StudentsNotExistException extends RuntimeException{

    public StudentsNotExistException(){
        super("No se encontraron estudiantes en la base de datos");
    }
    
}
