package com.devsenior.nmanja.university_campus_management_system.exceptions;

public class StudentAlreadyEnrolled extends RuntimeException{

    public StudentAlreadyEnrolled(Long id){
        super("El estudiante con el ID "+ id + " ya se encuentra inscrito a este curso");
    }
    
}
