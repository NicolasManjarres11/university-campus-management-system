package com.devsenior.nmanja.university_campus_management_system.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;

public record CourseUpdateRequest(
    @Schema(
        description = "Nombre completo del curso (opcional para actualización)",
        example = "Matemáticas Avanzadas"
    )
    String courseName,

    @Schema(
        description = "Código único del curso que sigue el formato ABC-123 (opcional para actualización)",
        example = "MAT-201"
    )
    @Pattern(
        regexp = "^[A-Z]{3}-\\d{3}$",
        message = "El código debe tener el formato ABC-123"
    )
    String courseCode,

    @Schema(
        description = "Descripción detallada del contenido y objetivos del curso (opcional para actualización)",
        example = "Curso avanzado de matemáticas que cubre cálculo diferencial e integral, álgebra lineal y ecuaciones diferenciales."
    )
    String description,

    @Schema(
        description = "Número máximo de estudiantes que pueden inscribirse en el curso (opcional para actualización)",
        example = "25"
    )
    @Min(value = 10, message = "La capacidad máxima debe ser al menos de 10 estudiantes")
    Integer maxStudents,

    @Schema(
        description = "Número actual de estudiantes inscritos en el curso (opcional para actualización)",
        example = "15"
    )
    @Min(value = 0)
    Integer studentsInCourse,

    @Schema(
        description = "Identificador del profesor que dictará el curso (opcional para actualización)",
        example = "1"
    )
    @JsonProperty("professor")
    Long professorId
) {
}
