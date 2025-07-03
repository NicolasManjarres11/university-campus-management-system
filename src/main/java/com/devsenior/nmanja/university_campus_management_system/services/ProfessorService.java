package com.devsenior.nmanja.university_campus_management_system.services;

import java.util.List;

import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorUpdateRequest;


public interface ProfessorService {

    List<ProfessorResponse> getAllProfessors();

    ProfessorResponse getProfessorById(Long id);

    ProfessorResponse createProfessor(ProfessorRequest professor);

    ProfessorResponse updateProfessor(Long id, ProfessorUpdateRequest professor);

    ProfessorResponse deleteProfessor(Long id);

    
}
