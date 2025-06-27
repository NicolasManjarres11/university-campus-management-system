package com.devsenior.nmanja.university_campus_management_system.services;

import java.util.List;

import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentResponse;

public interface StudentService {

    //Obtener todos los estudiantes
    List<StudentResponse> getAllStudents();

    //Obtener estudiante por ID
    StudentResponse getStudentById(Long id);

    StudentResponse createStudent(StudentRequest student);

    //Actualizar informacion de estudiante
    StudentResponse updateStudent(Long id, StudentRequest student);

    //Eliminar estudiante
    StudentResponse deleteStudent(Long id);

}
