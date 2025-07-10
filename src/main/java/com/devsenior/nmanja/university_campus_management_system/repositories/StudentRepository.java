package com.devsenior.nmanja.university_campus_management_system.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsenior.nmanja.university_campus_management_system.model.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUserUsername(String username);

    //Método para paginación y filtrado

    @Query("SELECT s FROM Student s WHERE " +
            "(:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:email IS NULL OR LOWER(s.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
            "(:studentNumber IS NULL OR s.studentNumber LIKE CONCAT('%', :studentNumber, '%'))")

    Page<Student> findAllWithFilters(
            Pageable pageable,
            @Param("name") String name,
            @Param("email") String email,
            @Param("studentNumber") String studentNumber);
}
