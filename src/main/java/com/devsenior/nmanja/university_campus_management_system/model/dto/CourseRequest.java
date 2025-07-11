package com.devsenior.nmanja.university_campus_management_system.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;

public record CourseRequest(
    @Schema(
        description = "Nombre completo del curso",
        example = "Matemáticas Avanzadas",
        required = true
    )
    @NotBlank(message = "El nombre del curso es obligatorio")
    String courseName,

    @Schema(
        description = "Código único del curso que sigue el formato ABC-123",
        example = "MAT-201",
        required = true
    )
    @Pattern(
        regexp = "^[A-Z]{3}-\\d{3}$",
        message = "El código debe tener el formato ABC-123"
    )
    @NotBlank(message = "El código del curso es obligatorio")
    String courseCode,

    @Schema(
        description = "Descripción detallada del contenido y objetivos del curso",
        example = "Curso avanzado de matemáticas que cubre cálculo diferencial e integral, álgebra lineal y ecuaciones diferenciales.",
        required = true
    )
    @NotBlank(message = "La descripción del curso es obligatoria")
    String description,

    @Schema(
        description = "Número máximo de estudiantes que pueden inscribirse en el curso",
        example = "25",
        required = true
    )
    @NotNull(message = "La capacidad máxima del curso es obligatoria")
    @Min(value = 10, message = "La capacidad máxima debe ser al menos de 10 estudiantes")
    Integer maxStudents,

    @Schema(
        description = "Identificador del profesor que dictará el curso",
        example = "1",
        required = true
    )
    @NotNull(message = "Es obligatorio relacionar el id del profesor")
    Long professorId
) {
}
