package com.devsenior.nmanja.university_campus_management_system.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthenticationResponse(
    @Schema(
        description = "Token JWT válido para autenticación en el sistema",
        example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJtYXJpYS5nYXJjaWEiLCJyb2xlcyI6WyJST0xFX1NUVURFTlQiXSwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
        required = true
    )
    String jwt
) {
}
