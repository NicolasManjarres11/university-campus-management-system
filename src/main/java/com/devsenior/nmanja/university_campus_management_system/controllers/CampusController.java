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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/campus")
@Tag(
    name = "Sistema de Gestión del Campus Universitario",
    description = "Operaciones para gestionar estudiantes, profesores, cursos e inscripciones del campus universitario"
)
public class CampusController {

    private final StudentService studentService;
    private final ProfessorService professorService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    // Estudiantes

    // Obtener todos los estudiantes

    @Operation(summary = "Obtener todos los estudiantes.")
    @ApiResponse(responseCode = "200", description = "Estudiantes encontrados.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontraron estudiantes.")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/students")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Obtener estudiante por ID

    @Operation(summary = "Obtener estudiante por ID.")
    @ApiResponse(responseCode = "200", description = "Estudiante encontrado.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontró un estudiante con el ID relacionado.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/students/{id}")
    public StudentResponse getStudentById(@PathVariable Long id, Authentication auth) {

        var username = auth.getName();

        return studentService.getStudentById(id, username);

    }

    // Registrar un estudiante


    @Operation(summary = "Registrar un estudiante.")
    @ApiResponse(responseCode = "201", description = "Estudiante creado exitosamente.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "400", description = "Los datos ingresados no son válidos.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/students")
    public StudentResponse createStudent(@Valid @RequestBody StudentRequest student) {
        return studentService.createStudent(student);
    }

    // Actualizar un estudiante

    @Operation(summary = "Actualizar un estudiante.")
    @ApiResponse(responseCode = "200", description = "Estudiante actualizado exitosamente.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontró un estudiante con el ID relacionado.")
    @ApiResponse(responseCode = "400", description = "Los datos ingresados no son válidos.")
    @ResponseStatus(HttpStatus.OK)
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


    @Operation(summary = "Eliminar un estudiante.")
    @ApiResponse(responseCode = "200", description = "Estudiante eliminado exitosamente.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontró un estudiante con el ID relacionado.")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/students/{id}")
    public StudentResponse deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    // Paginación y filtrado
/*     @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/students/all")

    public Page<StudentResponse> getAllStudentsWhitPage(
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
    } */

    // Profesores

    // Obtener todos los profesores

    @Operation(summary = "Obtener todos los profesores.")
    @ApiResponse(responseCode = "200", description = "Profesores encontrados.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontró profesores.")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/professors")
    public List<ProfessorResponse> getAllProfessors() {
        return professorService.getAllProfessors();
    }

    // Obtener profesor por ID

    @Operation(summary = "Obtener profesor por ID.")
    @ApiResponse(responseCode = "200", description = "Profesor encontrado.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontró profesor con el ID relacionado.")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/professors/{id}")
    public ProfessorResponse getProfessorById(@PathVariable Long id) {
        return professorService.getProfessorById(id);
    }

    // Crear profesor


    @Operation(summary = "Registrar profesor.")
    @ApiResponse(responseCode = "201", description = "Profesor registrado correctamente.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "400", description = "Los datos ingresados no son válidos.")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/professors")
    public ProfessorResponse createProfessor(@Valid @RequestBody ProfessorRequest professor) {

        return professorService.createProfessor(professor);
    }

    // Actualizar profesor

    @Operation(summary = "Actualizar datos de profesor.")
    @ApiResponse(responseCode = "200", description = "Profesor actualizado exitosamente.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontró profesor con el ID relacionado.")
    @ApiResponse(responseCode = "400", description = "Los datos ingresados no son válidos.")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/professors/{id}")
    public ProfessorResponse updateProfessor(@PathVariable Long id,
            @Valid @RequestBody ProfessorUpdateRequest professor) {

        return professorService.updateProfessor(id, professor);
    }

    // Eliminar profesor

    @Operation(summary = "Eliminar profesor.")
    @ApiResponse(responseCode = "200", description = "Profesor eliminado exitosamente.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontró profesor con el ID relacionado.")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/professors/{id}")
    public ProfessorResponse deleteProfessor(@PathVariable Long id) {
        return professorService.deleteProfessor(id);
    }

    // Cursos

    // Obtener todos los cursos

    @Operation(summary = "Obtener todos los cursos.")
    @ApiResponse(responseCode = "200", description = "Cursos encontrados.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontraron cursos.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/courses")
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }


    // Obtener curso por ID

    @Operation(summary = "Obtener curso por ID.")
    @ApiResponse(responseCode = "200", description = "Curso encontrado.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontró un curso con el ID relacionado.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/courses/{id}")
    public CourseResponse getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    // Crear un curso

    @Operation(summary = "Crear un curso.")
    @ApiResponse(responseCode = "201", description = "Cursos creado exitosamente.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "400", description = "Los datos ingresados no son válidos.")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/courses")
    public CourseResponse createCourse(@Valid @RequestBody CourseRequest course) {
        return courseService.createCourse(course);
    }

    //Actualizar un curso

    @Operation(summary = "Actualizar un curso.")
    @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontró un curso con el ID relacionado.")
    @ApiResponse(responseCode = "400", description = "Los datos ingresados no son válidos.")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/courses/{id}")
    public CourseResponse updateCourse(@PathVariable Long id, @Valid @RequestBody CourseUpdateRequest course) {

        return courseService.updateCourse(id, course);
    }

    // Borrar un curso

    @Operation(summary = "Eliminar un curso.")
    @ApiResponse(responseCode = "200", description = "Curso eliminado correctamente.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontró un curso con el ID relacionado.")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/courses/{id}")
    public CourseResponse deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourse(id);
    }

    // Inscripciones

    // Obtener todas las inscripciones

    @Operation(summary = "Obtener todas las inscripciones.")
    @ApiResponse(responseCode = "200", description = "Inscripciones encontradas.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontraron inscripciones.")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/enrollments")
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // Obtener inscripción por ID

    @Operation(summary = "Obtener inscripción por ID.")
    @ApiResponse(responseCode = "200", description = "Inscripción encontrada.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontró inscripción con el ID relacionado.")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/enrollments/{id}")
    public EnrollmentResponse getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    // Crear inscripción

    @Operation(summary = "Realizas una inscripción.")
    @ApiResponse(responseCode = "201", description = "Inscripción realizada exitosamente.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontraron los ID relacionados.")
    @ApiResponse(responseCode = "400", description = "Los datos ingresados no son válidos.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/enrollments")
    public EnrollmentResponse createEnrollments(@RequestBody EnrollmentRequest enrollment, Authentication auth) {

        var username = auth.getName();
        var roles = auth.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .toList();

        return enrollmentService.createEnrollment(enrollment, username, roles);
    }

    // Actualizar estado de inscripción

    @Operation(summary = "Actualizar estado de la inscripción.")
    @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontró inscripción con el ID relacionado.")
    @ApiResponse(responseCode = "400", description = "Los datos ingresados no son válidos.")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/enrollments/{id}")
    public EnrollmentResponse updateEnrollment(@PathVariable Long id,
            @Valid @RequestBody EnrollmentUpdateRequest status) {

        return enrollmentService.updateEnrollment(id, status);
    }

    // Cancelar inscripción

    @Operation(summary = "Cancelar inscripción.")
    @ApiResponse(responseCode = "200", description = "Inscripción cancelada.")
    @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar esta acción.")
    @ApiResponse(responseCode = "404", description = "No se encontró inscripción con el ID relacionado.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/enrollments/{id}")
    public EnrollmentResponse cancelEnrollment(@PathVariable Long id, Authentication auth) {

        var username = auth.getName();

        var roles = auth.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .toList();

        return enrollmentService.cancelEnrollment(id, username, roles);
    }

}
