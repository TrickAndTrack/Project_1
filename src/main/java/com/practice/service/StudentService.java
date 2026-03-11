package com.practice.service;

import com.practice.dto.StudentRequestDTO;
import com.practice.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {

    StudentResponseDTO createStudent(StudentRequestDTO dto);

    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO getStudent(Long id);

    StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto);

    void deleteStudent(Long id);
}