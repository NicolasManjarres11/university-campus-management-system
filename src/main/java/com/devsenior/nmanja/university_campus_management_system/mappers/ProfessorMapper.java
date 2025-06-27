package com.devsenior.nmanja.university_campus_management_system.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.ProfessorResponse;
import com.devsenior.nmanja.university_campus_management_system.model.entities.Professor;
import com.devsenior.nmanja.university_campus_management_system.model.summaries.ProfessorSummary;

@Mapper(componentModel = "spring"/* , uses = {CourseMapper.class} */)
public interface ProfessorMapper {

/*     ProfessorMapper INSTANCE = Mappers.getMapper(ProfessorMapper.class); */

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courses", ignore = true)
    Professor toEntity(ProfessorRequest professor);

    @Mapping(target = "courses", source = "courses")
    ProfessorResponse toResponse(Professor professor);

    ProfessorSummary toSummary(Professor professor);
    
}
