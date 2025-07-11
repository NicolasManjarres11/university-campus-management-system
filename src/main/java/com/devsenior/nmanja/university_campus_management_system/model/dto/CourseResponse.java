package com.devsenior.nmanja.university_campus_management_system.model.dto;

import com.devsenior.nmanja.university_campus_management_system.model.summaries.ProfessorSummary;
import io.swagger.v3.oas.annotations.media.Schema;

public record CourseResponse(
    @Schema(
        description = "Identificador único del curso",
        example = "1"
    )
    Long id,

    @Schema(
        description = "Nombre completo del curso",
        example = "Matemáticas Avanzadas"
    )
    String courseName,

    @Schema(
        description = "Código único del curso",
        example = "MAT-201"
    )
    String courseCode,

    @Schema(
        description = "Descripción detallada del contenido y objetivos del curso",
        example = "Curso avanzado de matemáticas que cubre cálculo diferencial e integral, álgebra lineal y ecuaciones diferenciales."
    )
    String description,

    @Schema(
        description = "Número máximo de estudiantes que pueden inscribirse en el curso",
        example = "25"
    )
    Integer maxStudents,

    @Schema(
        description = "Número actual de estudiantes inscritos en el curso",
        example = "15"
    )
    Integer studentsInCourse,

    @Schema(
        description = "Información del profesor que dicta el curso"
    )
    ProfessorSummary professor
) {
}
