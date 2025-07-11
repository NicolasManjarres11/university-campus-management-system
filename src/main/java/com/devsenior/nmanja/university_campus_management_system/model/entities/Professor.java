package com.devsenior.nmanja.university_campus_management_system.model.entities;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "professors")

public class Professor {

    @Schema(
        description = "Identificador único del profesor",
        example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
        description = "Nombre completo del profesor",
        example = "Dr. Juan Carlos Pérez González"
    )
    @Column(nullable = false)
    private String name;

    @Schema(
        description = "Departamento académico al que pertenece el profesor",
        example = "Matemáticas",
        allowableValues = {"Matemáticas", "Física", "Química", "Informática", "Ingeniería", "Administración"}
    )
    @Column(nullable = false)
    private String department;

    @Schema(
        description = "Dirección de correo electrónico única del profesor",
        example = "juan.perez@devsenior.edu.co",
        format = "email"
    ) 
    @Column(unique = true, nullable = false)
    private String email;

    //Cursos que dicta el profesor
    //Relación de uno a muchos
    //El profesor puede dictar varios cursos, pero un curso solo puede ser dictado por un profesor

    @Schema(
        description = "Dirección de correo electrónico única del profesor",
        example = "juan.perez@devsenior.edu.co",
        format = "email"
    )
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses;

    
}
