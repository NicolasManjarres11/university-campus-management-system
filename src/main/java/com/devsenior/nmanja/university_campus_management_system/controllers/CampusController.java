package com.devsenior.nmanja.university_campus_management_system.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentResponse;
import com.devsenior.nmanja.university_campus_management_system.services.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/campus")
/* @RequiredArgsConstructor */
public class CampusController {

    private final StudentService studentService;

    @GetMapping("/students")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    
}
    

