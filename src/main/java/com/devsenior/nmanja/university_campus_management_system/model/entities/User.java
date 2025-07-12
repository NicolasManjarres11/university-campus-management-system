package com.devsenior.nmanja.university_campus_management_system.model.entities;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@Table(name = "users")

public class User {

    @Schema(
        description = "Identificador único del usuario en el sistema",
        example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
        description = "Nombre de usuario único para autenticación",
        example = "maria.garcia"

    )
    @Column(nullable = false, unique = true)
    private String username;

    @Schema(
        description = "Contraseña encriptada del usuario",
        example = "$2a$10$encryptedPasswordHash",
        writeOnly = true
    )
    @Column(nullable = false)
    private String password;

    @Schema(
        description = "Roles asignados al usuario para autorización",
        example = "[\"ROLE_STUDENT\"]",
        allowableValues = {"ROLE_ADMIN", "ROLE_STUDENT"}
    )
    @ElementCollection(fetch = FetchType.EAGER) //Carga los roles inmediatamente con el usuario
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;
    
}
