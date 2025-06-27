package com.devsenior.nmanja.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.devsenior.nmanja.university_campus_management_system.model.dto.EnrollmentRequest;
import com.devsenior.nmanja.university_campus_management_system.model.dto.EnrollmentResponse;
import com.devsenior.nmanja.university_campus_management_system.model.entities.Enrollment;
import com.devsenior.nmanja.university_campus_management_system.model.summaries.EnrollmentSummary;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, StudentMapper.class})
public interface EnrollmentMapper {

/*     EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class); */
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inscriptionDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course", ignore = true)
    Enrollment toEntity(EnrollmentRequest enrollment);


    EnrollmentResponse toResponse(Enrollment enrollment);

    EnrollmentSummary toSummary(Enrollment enrollment);

}
