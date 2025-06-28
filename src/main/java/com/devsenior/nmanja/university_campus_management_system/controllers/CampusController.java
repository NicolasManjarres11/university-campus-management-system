package com.devsenior.nmanja.university_campus_management_system.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.nmanja.university_campus_management_system.model.dto.CourseRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.CourseResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorUpdateRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentUpdateRequest;
import com.devsenior.nmanja.university_campus_management_system.services.CourseService;
import com.devsenior.nmanja.university_campus_management_system.services.ProfessorService;
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
public class CampusController {

    private final StudentService studentService;
    private final ProfessorService professorService;
    private final CourseService courseService;

    //Estudiantes

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
    


    //Profesores

    //Obtener todos los profesores

    @GetMapping("/professors")
    public List<ProfessorResponse> getAllProfessors() {
        return professorService.getAllProfessors();
    }

    //Obtener profesor por ID

    @GetMapping("/professors/{id}")
    public ProfessorResponse getProfessorById(@PathVariable Long id) {
        return professorService.getProfessorById(id);
    }

    //Crear profesor
    @PostMapping("/professors")
    public ProfessorResponse createProfessor(@Valid @RequestBody ProfessorRequest professor) {

        return professorService.createProfessor(professor);
    }

    //Actualizar profesor
    @PutMapping("/professors/{id}")
    public ProfessorResponse updateProfessor(@PathVariable Long id, @Valid @RequestBody ProfessorUpdateRequest professor) {
        
        return professorService.updateProfessor(id, professor);
    }

    //Eliminar profesor
    @DeleteMapping("/professors/{id}")
    public ProfessorResponse deleteProfessor(@PathVariable Long id){
        return professorService.deleteProfessor(id);
    }
    
    
    //Cursos

    //Obtener todos los cursos
    @GetMapping("/courses")
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }

    //Obtener curso por ID
    @GetMapping("/courses/{id}")
    public CourseResponse getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    //Crear un curso
    @PostMapping("/courses")
    public CourseResponse createCourse(@Valid @RequestBody CourseRequest course) {
        return courseService.createCourse(course);
    }
    
    //Borrar un curso
    @DeleteMapping("/courses/{id}")
    public CourseResponse deleteCourse(@PathVariable Long id){
        return courseService.deleteCourse(id);
    }
    

    
    
}
    

