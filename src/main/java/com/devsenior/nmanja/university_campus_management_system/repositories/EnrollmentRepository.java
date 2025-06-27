package com.devsenior.nmanja.university_campus_management_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsenior.nmanja.university_campus_management_system.model.entities.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{
    
}
