package com.devsenior.nmanja.university_campus_management_system.model.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@Table(name = "students")

public class Student {
    
    @Schema(
        description = "Identificador único del estudiante",
        example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
        description = "Nombre completo del estudiante",
        example = "María Alejandra García López"
    )
    @Column(nullable = false)
    private String name;

    @Schema(
        description = "Dirección de correo electrónico institucional del estudiante",
        example = "maria.garcia@devsenior.edu.co",
        format = "email"
    )
    @Column(unique = true, nullable = false)
    private String email;

    @Schema(
        description = "Número de identificación único del estudiante",
        example = "2024001"
    )
    @Column(unique = true, nullable = false)
    private String studentNumber;

    @Schema(
        description = "Colección de todas las inscripciones del estudiante en cursos. Incluye inscripciones activas, completadas y retiradas.",
        example = "[{\"id\": 1, \"course\": {\"courseName\": \"Matemáticas Avanzadas\", \"courseCode\": \"MAT-201\"}, \"status\": \"ACTIVO\", \"inscriptionDate\": \"2024-01-15\"}]"
    )
    //Relación de uno a muchos
    //Un estudiante puede tener varias inscripciones

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments;

    @Schema(
        description = "Cuenta de usuario asociada al estudiante para autenticación",
        example = "{\"id\": 1, \"username\": \"maria.garcia\", \"roles\": [\"ROLE_STUDENT\"]}"
    )
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;



}
