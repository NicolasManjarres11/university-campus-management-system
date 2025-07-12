package com.devsenior.nmanja.university_campus_management_system.services;

import com.devsenior.nmanja.university_campus_management_system.model.dto.AuthenticationRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest credentials);
}
