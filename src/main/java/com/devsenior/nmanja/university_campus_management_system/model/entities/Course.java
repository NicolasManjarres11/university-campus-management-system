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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")

public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del curso", example = "17")
    private Long id;

    @Schema(description = "Nombre del curso", example = "Física Mecánica")
    @Column(nullable = false)
    private String courseName;

    @Schema(description = "Código del curso", example = "FSM-109")
    @Column(unique = true, nullable = false)
    private String courseCode;

    @Schema(description = "Descripción del curso", example = "En este curso aprenderas los conceptos básicos sobre la física mecánica.")
    @Column(nullable = false)
    private String description;

    @Schema(description = "Cantidad máxima de extudiantes", example = "20")
    @Column(nullable = false)
    private Integer maxStudents;


    @Schema(
        description = "Número actual de estudiantes inscritos en el curso",
        example = "15",
        minimum = "0"
    )
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer studentsInCourse;

    //Relacion de muchos a uno con la tabla professors
    //Varios cursos pueden ser dictados por un profesor

    @Schema(
        description = "Profesor asignado al curso",
        example = "{\"id\": 1, \"name\": \"Dr. Juan Pérez\", \"department\": \"Matemáticas\"}"
    )
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Schema(
        description = "Lista de inscripciones asociadas al curso",
        example = "[]"
    )
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments;

    

}
