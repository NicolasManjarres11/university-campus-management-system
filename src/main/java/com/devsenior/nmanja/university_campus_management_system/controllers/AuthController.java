package com.devsenior.nmanja.university_campus_management_system.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.devsenior.nmanja.university_campus_management_system.model.dto.AuthenticationRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.AuthenticationResponse;
import com.devsenior.nmanja.university_campus_management_system.services.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@RequiredArgsConstructor
@RestController
@Tag(
    name = "Gestión de autenticaciones",
    description = "Operaciones para gestionar las autenticaciones realizadas en el sistema de gestión del Campus Universitario."
)
public class AuthController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Autenticación del usuarios.")
    @ApiResponse(responseCode = "200", description = "Autenticación exitosa encontrados.")
    @ApiResponse(responseCode = "409", description = "Credenciales inválidas.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest auth) {

        
        return authenticationService.login(auth);
    }
    
    
}
