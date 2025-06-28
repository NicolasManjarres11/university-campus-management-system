package com.devsenior.nmanja.university_campus_management_system.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentUpdateRequest;
import com.devsenior.nmanja.university_campus_management_system.services.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




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
    public StudentResponse createStudent(@Valid @RequestBody StudentRequest student) {        
        return studentService.createStudent(student);
    }

    //Actualizar un estudiante
    @PutMapping("/students/{id}")
    public StudentResponse updateStudent(@PathVariable Long id,@Valid @RequestBody StudentUpdateRequest student) {
        
        return studentService.updateStudent(id, student);
    }
    
    //Eliminar un estudiante
    @DeleteMapping("/students/{id}")
    public StudentResponse deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }
    
    
    
}
    

