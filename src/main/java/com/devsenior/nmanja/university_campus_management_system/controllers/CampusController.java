package com.devsenior.nmanja.university_campus_management_system.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.nmanja.university_campus_management_system.model.dto.CourseRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.CourseResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.CourseUpdateRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.EnrollmentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.EnrollmentResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.EnrollmentUpdateRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorUpdateRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentUpdateRequest;
import com.devsenior.nmanja.university_campus_management_system.services.CourseService;
import com.devsenior.nmanja.university_campus_management_system.services.EnrollmentService;
import com.devsenior.nmanja.university_campus_management_system.services.ProfessorService;
import com.devsenior.nmanja.university_campus_management_system.services.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    private final EnrollmentService enrollmentService;

    // Estudiantes

    // Obtener todos los estudiantes
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/students")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Obtener estudiante por ID

    @GetMapping("/students/{id}")
    public StudentResponse getStudentById(@PathVariable Long id, Authentication auth) {

        var username = auth.getName();

        return studentService.getStudentById(id, username);

    }

    // Registrar un estudiante
    @PostMapping("/students")
    public StudentResponse createStudent(@Valid @RequestBody StudentRequest student) {
        return studentService.createStudent(student);
    }

    // Actualizar un estudiante
    @PutMapping("/students/{id}")
    public StudentResponse updateStudent(@PathVariable Long id, @Valid @RequestBody StudentUpdateRequest student,
            Authentication auth) {

        var username = auth.getName();
        var roles = auth.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .toList();

        return studentService.updateStudent(id, student, username, roles);
    }

    // Eliminar un estudiante
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/students/{id}")
    public StudentResponse deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    // Paginación y filtrado

    public Page<StudentResponse> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String studentNumber,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        // Crear Pageable con ordenamiento
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return studentService.getAllStudentsWithFilters(
                pageable, name, email, studentNumber);
    }

    // Profesores

    // Obtener todos los profesores

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/professors")
    public List<ProfessorResponse> getAllProfessors() {
        return professorService.getAllProfessors();
    }

    // Obtener profesor por ID

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/professors/{id}")
    public ProfessorResponse getProfessorById(@PathVariable Long id) {
        return professorService.getProfessorById(id);
    }

    // Crear profesor

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/professors")
    public ProfessorResponse createProfessor(@Valid @RequestBody ProfessorRequest professor) {

        return professorService.createProfessor(professor);
    }

    // Actualizar profesor

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/professors/{id}")
    public ProfessorResponse updateProfessor(@PathVariable Long id,
            @Valid @RequestBody ProfessorUpdateRequest professor) {

        return professorService.updateProfessor(id, professor);
    }

    // Eliminar profesor

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/professors/{id}")
    public ProfessorResponse deleteProfessor(@PathVariable Long id) {
        return professorService.deleteProfessor(id);
    }

    // Cursos

    // Obtener todos los cursos
    @GetMapping("/courses")
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Obtener curso por ID
    @GetMapping("/courses/{id}")
    public CourseResponse getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    // Crear un curso
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/courses")
    public CourseResponse createCourse(@Valid @RequestBody CourseRequest course) {
        return courseService.createCourse(course);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/courses/{id}")
    public CourseResponse updateCourse(@PathVariable Long id, @Valid @RequestBody CourseUpdateRequest course) {

        return courseService.updateCourse(id, course);
    }

    // Borrar un curso
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/courses/{id}")
    public CourseResponse deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourse(id);
    }

    // Inscripciones

    // Obtener todas las inscripciones

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/enrollments")
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // Obtener inscripción por ID

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/enrollments/{id}")
    public EnrollmentResponse getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    // Crear inscripción
    @PostMapping("/enrollments")
    public EnrollmentResponse createEnrollments(@RequestBody EnrollmentRequest enrollment, Authentication auth) {

        var username = auth.getName();
        var roles = auth.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .toList();

        return enrollmentService.createEnrollment(enrollment, username, roles);
    }

    // Actualizar estado de inscripción
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/enrollments/{id}")
    public EnrollmentResponse updateEnrollment(@PathVariable Long id,
            @Valid @RequestBody EnrollmentUpdateRequest status) {

        return enrollmentService.updateEnrollment(id, status);
    }

    // Cancelar inscripción
    @DeleteMapping("/enrollments/{id}")
    public EnrollmentResponse cancelEnrollment(@PathVariable Long id, Authentication auth) {

        var username = auth.getName();

        var roles = auth.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .toList();

        return enrollmentService.cancelEnrollment(id, username, roles);
    }

}
