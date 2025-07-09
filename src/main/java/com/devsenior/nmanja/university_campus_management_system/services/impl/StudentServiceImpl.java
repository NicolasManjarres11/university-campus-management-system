package com.devsenior.nmanja.university_campus_management_system.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devsenior.nmanja.university_campus_management_system.exceptions.EntityNotFoundException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.UserAlreadyExistException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.EntityNotExistException;
import com.devsenior.nmanja.university_campus_management_system.helper.UpdateHelper;
import com.devsenior.nmanja.university_campus_management_system.mappers.StudentMapper;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentUpdateRequest;
import com.devsenior.nmanja.university_campus_management_system.model.entities.Student;
import com.devsenior.nmanja.university_campus_management_system.model.entities.User;
import com.devsenior.nmanja.university_campus_management_system.repositories.StudentRepository;
import com.devsenior.nmanja.university_campus_management_system.repositories.UserRepository;
import com.devsenior.nmanja.university_campus_management_system.services.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    
    private final StudentMapper studentMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    //Obtener todos los estudiantes
    @Override
    public List<StudentResponse> getAllStudents() {

        var entity = studentRepository.findAll();

        if(entity.isEmpty()){
            throw new EntityNotExistException("estudiantes");
        }

        var response = entity.stream()
            .map(s -> studentMapper.toResponse(s))
            .toList();

        return response;
    }

    //Obtener estudiante por ID

    @Override
    public StudentResponse getStudentById(Long id, String username) {
        


        var student = studentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id,"estudiante"));

        var user = findByUserUsername(username);

        if(!user.getId().equals(id)){
            throw new AuthorizationDeniedException("No tienes permiso para ver este estudiante");
        }

        
        return studentMapper.toResponse(student);
    }

    //Registrar un nuevo estudiante

    @Override
    public StudentResponse createStudent(StudentRequest student) {

        if(userRepository.existsByUsername(student.username())){

            throw new UserAlreadyExistException(student.username());
        }

        var user = new User();

        user.setUsername(student.username());
        user.setPassword(passwordEncoder.encode(student.password()));
        user.setRoles(Set.of("ROLE_STUDENT"));
        user = userRepository.save(user);
        
        var entity = studentMapper.toEntity(student);

        entity.setEnrollments(new ArrayList<>()); //Guardamos con una lista vacia con el fin de no devolver un valor nulo
        entity.setUser(user);


        studentRepository.save(entity);

        return studentMapper.toResponse(entity);
    }

    //Actualizar un estudiante
    @Override
    public StudentResponse updateStudent(Long id, StudentUpdateRequest student, String username, List<String> roles) {

        var entityOptional = studentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id,"estudiante"));

        //Actualización por parte del administrador

        if(roles.contains("ROLE_ADMIN")){

            UpdateHelper.updateIfNotNull(entityOptional, student);

            var updateEntity = studentRepository.save(entityOptional);
    
    
            return studentMapper.toResponse(updateEntity);
            
        }

        //Actualización por parte del estudiante
        var user = findByUserUsername(username);

        if(!user.getId().equals(id)){
            throw new AuthorizationDeniedException("No tienes permiso para actualizar este estudiante");
        }

        UpdateHelper.updateIfNotNull(entityOptional, student);

        var updateEntity = studentRepository.save(entityOptional);


        return studentMapper.toResponse(updateEntity);
    }


    //Eliminar un estudiante
    @Override
    public StudentResponse deleteStudent(Long id) {
        var entityOptional = studentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id,"estudiante"));


        studentRepository.deleteById(id);

        return studentMapper.toResponse(entityOptional);
    }

    
    //Buscar estudiante por username

    @Override
    public Student findByUserUsername(String username) {

        return studentRepository.findByUserUsername(username).
        orElseThrow(() -> new EntityNotFoundException("No se encontró estudiante con el usuario: "+username));
    }
    


    
}
