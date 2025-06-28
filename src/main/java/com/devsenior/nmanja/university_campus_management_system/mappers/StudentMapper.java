package com.devsenior.nmanja.university_campus_management_system.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentResponse;
import com.devsenior.nmanja.university_campus_management_system.model.dto.StudentUpdateRequest;
import com.devsenior.nmanja.university_campus_management_system.model.entities.Student;
import com.devsenior.nmanja.university_campus_management_system.model.summaries.StudentSummary;

/* @Component */
@Mapper(componentModel = "spring", uses = EnrollmentMapper.class)
public interface StudentMapper {

/*     StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class); */ 

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    Student toEntity(StudentRequest student);


    @Mapping(target = "enrollments", source = "enrollments")
    StudentResponse toResponse(Student student);

    StudentSummary toSummary(Student student);
    
}
