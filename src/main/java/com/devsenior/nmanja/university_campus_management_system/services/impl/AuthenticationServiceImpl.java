package com.devsenior.nmanja.university_campus_management_system.services.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.devsenior.nmanja.university_campus_management_system.model.dto.AuthenticationRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.AuthenticationResponse;
import com.devsenior.nmanja.university_campus_management_system.services.AuthenticationService;
import com.devsenior.nmanja.university_campus_management_system.util.JwtUtil;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public AuthenticationResponse login(AuthenticationRequest credentials) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password())
        );

        var userDetails = userDetailsService.loadUserByUsername(credentials.username());

        var jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }
    
}
