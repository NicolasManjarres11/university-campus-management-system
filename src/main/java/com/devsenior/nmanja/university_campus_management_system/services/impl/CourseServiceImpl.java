package com.devsenior.nmanja.university_campus_management_system.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.nmanja.university_campus_management_system.exceptions.CourseFullException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.CourseWithoutStudentsException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.EntityNotExistException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.EntityNotFoundException;
import com.devsenior.nmanja.university_campus_management_system.helper.UpdateHelper;
import com.devsenior.nmanja.university_campus_management_system.mappers.CourseMapper;
import com.devsenior.nmanja.university_campus_management_system.model.dto.CourseRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.CourseResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.CourseUpdateRequest;
import com.devsenior.nmanja.university_campus_management_system.repositories.CourseRepository;
import com.devsenior.nmanja.university_campus_management_system.repositories.ProfessorRepository;
import com.devsenior.nmanja.university_campus_management_system.services.CourseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;


    private final ProfessorRepository professorRepository;

    //Obtener todos los cursos
    @Override
    public List<CourseResponse> getAllCourses() {

        var entity = courseRepository.findAll();

        if(entity.isEmpty()){
            throw new EntityNotExistException("cursos");
        }

        var reponse = entity.stream()
                .map(c -> courseMapper.toResponse(c))
                .toList();
        
        return reponse;
    }

    //Obtener curso por id
    @Override
    public CourseResponse getCourseById(Long id) {
        var entityOptional = courseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, "curso"));

        return courseMapper.toResponse(entityOptional);
    }

    //Crear curso
    @Override
    public CourseResponse createCourse(CourseRequest course) {
        
        //Validamos primero que el id del profesor exista
        var professor = professorRepository.findById(course.professorId())
        .orElseThrow(() -> new EntityNotFoundException(course.professorId(), "profesor"));

        var entity = courseMapper.toEntity(course);

        entity.setProfessor(professor);
        entity.setStudentsInCourse(0);

        courseRepository.save(entity);

        return courseMapper.toResponse(entity);
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseUpdateRequest course) {

        var entityOptional = courseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, "curso"));

        if(course.professorId() != null){
                    //Validamos primero que el id del profesor exista
        var professor = professorRepository.findById(course.professorId())
        .orElseThrow(() -> new EntityNotFoundException(course.professorId(), "profesor"));
        entityOptional.setProfessor(professor);
        }

        UpdateHelper.updateIfNotNull(entityOptional, course);

        var updateEntity = courseRepository.save(entityOptional);

        return courseMapper.toResponse(updateEntity);
    }

    @Override
    public CourseResponse deleteCourse(Long id) {

        var entityOptional = courseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, "curso"));

        courseRepository.deleteById(id);

        return courseMapper.toResponse(entityOptional);
    }

    @Override
    public void incrementStudentsInCourse(Long id) {
        
        var course = courseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, "curso"));

        if(course.getStudentsInCourse() >= course.getMaxStudents()){
            throw new CourseFullException(id);
        }


        course.setStudentsInCourse(course.getStudentsInCourse() + 1);

        courseRepository.save(course);
        
    }

    @Override
    public void decrementStudentsInCourse(Long id) {
        var course = courseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, "curso"));

        if(course.getStudentsInCourse() <= 0){
            throw new CourseWithoutStudentsException(id);
        }

        course.setStudentsInCourse(course.getStudentsInCourse() - 1);

        courseRepository.save(course);
        
    }


    
}
