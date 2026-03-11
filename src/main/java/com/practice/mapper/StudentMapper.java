package com.practice.mapper;

import com.practice.dto.StudentRequestDTO;
import com.practice.dto.StudentResponseDTO;
import com.practice.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentRequestDTO dto);

    StudentResponseDTO toDTO(Student student);
}