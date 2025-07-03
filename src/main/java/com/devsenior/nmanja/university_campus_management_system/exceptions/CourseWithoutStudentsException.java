package com.devsenior.nmanja.university_campus_management_system.exceptions;

public class CourseWithoutStudentsException extends RuntimeException{
    

    public CourseWithoutStudentsException(Long id){
        super("El curso con ID "+id+" No tiene estudiantes inscritos, por lo cual, no es posible realizar esta acción.");
    }

}
