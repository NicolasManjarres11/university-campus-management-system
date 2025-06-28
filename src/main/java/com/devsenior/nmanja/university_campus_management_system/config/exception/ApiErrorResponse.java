package com.devsenior.nmanja.university_campus_management_system.config.exception;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ApiErrorResponse {

 //Formato de fecha y hora
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    private Integer status;
    private String error;
    private String message;
    private String path;

    public ApiErrorResponse(HttpStatus status, String message, String path){

        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.path = path;

    }
    
}
