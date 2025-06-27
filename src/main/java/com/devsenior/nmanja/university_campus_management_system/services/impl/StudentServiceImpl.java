package com.devsenior.nmanja.university_campus_management_system.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentResponse;
import com.devsenior.nmanja.university_campus_management_system.repositories.StudentRepository;
import com.devsenior.nmanja.university_campus_management_system.services.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    public List<StudentResponse> getAllStudents() {
        var student = studentRepository.findAll();

        
        return null
            ;
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        // TODO Auto-generated method stub
        return null;
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
