package com.devsenior.nmanja.university_campus_management_system.config.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsenior.nmanja.university_campus_management_system.exceptions.EntityNotFoundException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.StatusNotValidException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.CourseFullException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.CourseWithoutStudentsException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.EntityNotExistException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //Etiquetamos esta clase para indicar que es la que se va a encargar de manejar las excepciones globales del controlador
public class GlobalHandlerException {

    //No hay estudiantes en la base de datos
    @ExceptionHandler(EntityNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotExistException(EntityNotExistException ex, HttpServletRequest request){

        var errorResponse = new ApiErrorResponse(
            HttpStatus.NOT_FOUND, 
            ex.getMessage(), 
            request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    //No existe el estudiante con el ID suministrado
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request){

        var errorResponse = new ApiErrorResponse(
            HttpStatus.NOT_FOUND, 
            ex.getMessage(), 
            request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    //El curso ya está lleno
    @ExceptionHandler(CourseFullException.class)
    public ResponseEntity<ApiErrorResponse> handleCourseFullException(CourseFullException ex, HttpServletRequest request){

        var errorResponse = new ApiErrorResponse(
            HttpStatus.CONFLICT, 
            ex.getMessage(), 
            request.getRequestURI());

            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);

    }

    @ExceptionHandler(CourseWithoutStudentsException.class)
    public ResponseEntity<ApiErrorResponse> handleCourseWithoutStudentsException(CourseWithoutStudentsException ex, HttpServletRequest request){

        var errorResponse = new ApiErrorResponse(
            HttpStatus.CONFLICT, 
            ex.getMessage(), 
            request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);

    }

    @ExceptionHandler(StatusNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleStatusNotValidException(StatusNotValidException ex, HttpServletRequest request){

        var errorResponse = new ApiErrorResponse(
            HttpStatus.CONFLICT, 
            ex.getMessage(),
            request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
    
    //Formato invalido
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidExceptio(MethodArgumentNotValidException ex, HttpServletRequest request){

        var errorResponse = new ApiErrorResponse(
            HttpStatus.BAD_REQUEST,
            ex.getMessage(), 
            request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    //Datos duplicados
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request){

        String message ="";

        if(ex.getMessage().contains("email")){
            message = "El correo ya se encuentra registrado";
        } else if (ex.getMessage().contains("student_numbe")) {
            message = "El número de estudiante ya se encuentra registrado";
        }

        var errorResponse = new ApiErrorResponse(
            HttpStatus.CONFLICT, 
            message, 
            request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

}
