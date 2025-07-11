package com.devsenior.nmanja.university_campus_management_system.model.entities;

import java.time.LocalDate;

import com.devsenior.nmanja.university_campus_management_system.model.enums.EnrollmentStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "enrollments")

public class Enrollment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la inscripción" , example = "4")
    private Long id;

    //Relación de muchos a uno (Esta tabla) con la tabla students
    //Muchos estudiantes pueden estar relacionados a varios cursos

    @Schema(
        description = "Estudiante inscrito en el curso",
        example = "{\"id\": 1, \"name\": \"María García\", \"email\": \"maria.garcia@devsenior.edu.co\"}"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    //Relación de muchos a uno (Esta tabla) con la tabla courses
    //Muchos cursos pueden estar relacionados a varios estudiantes

    @Schema(
        description = "Curso en el que está inscrito el estudiante",
        example = "{\"id\": 1, \"courseName\": \"Matemáticas Avanzadas\", \"courseCode\": \"MAT-201\"}"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Schema(
        description = "Fecha en que se realizó la inscripción",
        example = "2024-01-15",
        format = "date"
    )
    @Column(nullable = false)
    private LocalDate inscriptionDate;

    @Schema(
        description = "Estado actual de la inscripción",
        example = "ACTIVO",
        allowableValues = {"ACTIVO", "COMPLETADO", "RETIRADO"}
    )
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus status;

}
