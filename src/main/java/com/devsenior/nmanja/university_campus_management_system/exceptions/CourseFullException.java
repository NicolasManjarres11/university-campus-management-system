package com.devsenior.nmanja.university_campus_management_system.exceptions;

public class CourseFullException extends RuntimeException{
    
    public CourseFullException(Long id){
        super("El curso con el ID "+id+" ya está lleno");
    }

}
