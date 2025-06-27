package com.devsenior.nmanja.university_campus_management_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsenior.nmanja.university_campus_management_system.model.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{
    
}
