package com.devsenior.nmanja.university_campus_management_system.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequest(

    @NotBlank(message = "El código del curso es obligatorio")
    String courseCode,

    @NotBlank(message = "La descripción del curso es obligatoria")
    String description,

    @NotNull(message = "La capacidad máxima del curso es obligatoria")
    @Min(value = 10, message = "La capacidad máxima debe ser al menos de 10 estudiantes")
    Integer maxStudents,

    @NotNull(message = "Es obligatorio relacionar el id del profesor")
    Long professorId

) {
    
}
