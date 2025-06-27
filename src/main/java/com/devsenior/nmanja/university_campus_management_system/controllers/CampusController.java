package com.devsenior.nmanja.university_campus_management_system.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentResponse;
import com.devsenior.nmanja.university_campus_management_system.services.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/campus")
/* @RequiredArgsConstructor */
public class CampusController {

    private final StudentService studentService;


    //Obtener todos los estudiantes
    @GetMapping("/students")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    //Obtener estudiante por ID
    @GetMapping("/students/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    //Registrar un estudiante
    @PostMapping("/students")
    public StudentResponse createStudent(@RequestBody StudentRequest student) {
        //TODO: process POST request
        
        return studentService.createStudent(student);
    }
    
    
    
    
}
    

