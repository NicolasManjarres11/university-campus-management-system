package com.devsenior.nmanja.university_campus_management_system.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentUpdateRequest;
import com.devsenior.nmanja.university_campus_management_system.model.entities.Student;

public interface StudentService {

    //Obtener todos los estudiantes
    List<StudentResponse> getAllStudents();

    //Obtener estudiante por ID
    StudentResponse getStudentById(Long id, String username, List<String> roles);

    StudentResponse createStudent(StudentRequest student);

    //Actualizar informacion de estudiante
    StudentResponse updateStudent(Long id, StudentUpdateRequest student, String username, List<String> roles);

    //Eliminar estudiante
    StudentResponse deleteStudent(Long id);

    //Buscar por usuario de estudiante
    Student findByUserUsername(String username);

    //Paginación y filtrado

    Page<StudentResponse> getAllStudentsWithFilters(
        Pageable pageable, 
        String name, 
        String email, 
        String studentNumber);

}
