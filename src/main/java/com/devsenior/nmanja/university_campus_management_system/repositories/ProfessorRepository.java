package com.devsenior.nmanja.university_campus_management_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsenior.nmanja.university_campus_management_system.model.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor,Long>{
    
}
