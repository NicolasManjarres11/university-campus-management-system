package com.devsenior.nmanja.university_campus_management_system.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsenior.nmanja.university_campus_management_system.exceptions.StudentNotFoundException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.StudentsNotExistException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //Etiquetamos esta clase para indicar que es la que se va a encargar de manejar las excepciones globales del controlador
public class GlobalHandlerException {

    //No hay estudiantes en la base de datos
    @ExceptionHandler(StudentsNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleStudentNotExistException(StudentsNotExistException ex, HttpServletRequest request){

        var errorResponse = new ApiErrorResponse(
            HttpStatus.NOT_FOUND, 
            ex.getMessage(), 
            request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    //No existe el estudiante con el ID suministrado
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleStudentNotFound(StudentNotFoundException ex, HttpServletRequest request){

        var errorResponse = new ApiErrorResponse(
            HttpStatus.NOT_FOUND, 
            ex.getMessage(), 
            request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    
    //Formato invalido
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidExceptio(MethodArgumentNotValidException ex, HttpServletRequest request){

        var errorResponse = new ApiErrorResponse(
            HttpStatus.BAD_REQUEST,
            ex.getMessage(), 
            request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
