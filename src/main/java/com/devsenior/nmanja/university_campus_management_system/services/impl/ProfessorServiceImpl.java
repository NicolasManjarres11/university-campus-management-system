package com.devsenior.nmanja.university_campus_management_system.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.nmanja.university_campus_management_system.exceptions.EntityNotExistException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.EntityNotFoundException;
import com.devsenior.nmanja.university_campus_management_system.exceptions.ProfessorsNotExistException;
import com.devsenior.nmanja.university_campus_management_system.helper.UpdateHelper;
import com.devsenior.nmanja.university_campus_management_system.mappers.ProfessorMapper;
import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorUpdateRequest;
import com.devsenior.nmanja.university_campus_management_system.model.entities.Professor;
import com.devsenior.nmanja.university_campus_management_system.repositories.ProfessorRepository;
import com.devsenior.nmanja.university_campus_management_system.services.ProfessorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProfessorServiceImpl implements ProfessorService{

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    //Obtener todos los profesores
    @Override
    public List<ProfessorResponse> getAllProfessors() {

        var entity = professorRepository.findAll();

        if(entity.isEmpty()){
            throw new EntityNotExistException("profesores");
        }
        
        var response = entity.stream()
            .map(p -> professorMapper.toResponse(p))
            .toList()
        ;

        return response;
    }

    @Override
    public ProfessorResponse getProfessorById(Long id) {
        
        var entity = professorRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, "profesor"));

        return professorMapper.toResponse(entity);
    }

    @Override
    public ProfessorResponse createProfessor(ProfessorRequest professor) {

        var entity = professorMapper.toEntity(professor);

        entity.setCourses(new ArrayList<>());

        professorRepository.save(entity);

        return professorMapper.toResponse(entity);
    }

    @Override
    public ProfessorResponse updateProfessor(Long id, ProfessorUpdateRequest professor) {
        var entityOptional = professorRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, "profesor"));

        UpdateHelper.updateIfNotNull(entityOptional, professor);

        var updateEntity = professorRepository.save(entityOptional);

        return professorMapper.toResponse(updateEntity);
    }

    @Override
    public ProfessorResponse deleteProfessor(Long id) {

        var entityOptional = professorRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, "profesor"));

        professorRepository.deleteById(id);

        return professorMapper.toResponse(entityOptional);
    }

    
}
