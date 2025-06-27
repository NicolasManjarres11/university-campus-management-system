package com.devsenior.nmanja.university_campus_management_system.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.devsenior.nmanja.university_campus_management_system.model.dto.CourseRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.CourseResponse;
import com.devsenior.nmanja.university_campus_management_system.model.entities.Course;
import com.devsenior.nmanja.university_campus_management_system.model.summaries.CourseSummary;

@Mapper(componentModel = "spring", uses = ProfessorMapper.class)
public interface CourseMapper {
    

/*     CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class); */

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "professor", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    Course toEntity(CourseRequest course);

    @Mapping(target = "professor", source = "professor")
    CourseResponse toResponse(Course course);

    CourseSummary toSummary(Course course);

}
