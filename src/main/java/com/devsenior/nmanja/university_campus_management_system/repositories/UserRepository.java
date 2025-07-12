package com.devsenior.nmanja.university_campus_management_system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsenior.nmanja.university_campus_management_system.model.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
    
}
