package com.devsenior.nmanja.university_campus_management_system.services;

import java.util.List;

import com.devsenior.nmanja.university_campus_management_system.model.dto.CourseRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.CourseResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.CourseUpdateRequest;

public interface CourseService {

    List<CourseResponse> getAllCourses();

    CourseResponse getCourseById(Long id);

    CourseResponse createCourse(CourseRequest course);

    CourseResponse updateCourse(Long id, CourseUpdateRequest course);

    CourseResponse deleteCourse(Long id);
    
}
