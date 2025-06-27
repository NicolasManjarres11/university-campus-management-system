package com.devsenior.nmanja.university_campus_management_system.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.nmanja.university_campus_management_system.exceptions.StudentNotFoundException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.StudentsNotExistException;
import com.devsenior.nmanja.university_campus_management_system.mappers.StudentMapper;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentResponse;
import com.devsenior.nmanja.university_campus_management_system.repositories.StudentRepository;
import com.devsenior.nmanja.university_campus_management_system.services.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    
    private final StudentMapper studentMapper;

    //Obtener todos los estudiantes
    @Override
    public List<StudentResponse> getAllStudents() {

        var entity = studentRepository.findAll();

        if(entity.isEmpty()){
            throw new StudentsNotExistException();
        }

        var response = entity.stream()
            .map(s -> studentMapper.toResponse(s))
            .toList();

        return response;
    }

    //Obtener estudiante por ID

    @Override
    public StudentResponse getStudentById(Long id) {

        var student = studentRepository.findById(id)
            .map(s -> studentMapper.toResponse(s))
            .orElseThrow(() -> new StudentNotFoundException(id));
        
        return student;
    }

    //Registrar un nuevo estudiante

    @Override
    public StudentResponse createStudent(StudentRequest student) {
        
        var entity = studentMapper.toEntity(student);

        entity.setEnrollments(new ArrayList<>()); //Guardamos con una lista vacia con el fin de no devolver un valor nulo

        studentRepository.save(entity);

        return studentMapper.toResponse(entity);
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest student) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StudentResponse deleteStudent(Long id) {
        // TODO Auto-generated method stub
        return null;
    }


    
}
