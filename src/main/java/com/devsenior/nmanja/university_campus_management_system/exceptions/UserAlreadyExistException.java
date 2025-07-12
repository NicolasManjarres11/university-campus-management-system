package com.devsenior.nmanja.university_campus_management_system.exceptions;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String username){
        super("El username '"+username+"' ya existe. Por favor, elija uno direfente.");
    }
    
}
