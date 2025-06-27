package com.devsenior.nmanja.university_campus_management_system.model.entities;

import java.time.LocalDate;

import com.devsenior.nmanja.university_campus_management_system.model.enums.EnrollmentStatus;

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
    private Long id;

    //Relación de muchos a uno (Esta tabla) con la tabla students
    //Muchos estudiantes pueden estar relacionados a varios cursos

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    //Relación de muchos a uno (Esta tabla) con la tabla courses
    //Muchos cursos pueden estar relacionados a varios estudiantes

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false)
    private LocalDate inscriptionDate;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus status;

}
