package com.practice.service.impl;

import com.practice.dto.StudentRequestDTO;
import com.practice.dto.StudentResponseDTO;
import com.practice.entity.Student;
import com.practice.exception.ResourceNotFoundException;
import com.practice.mapper.StudentMapper;
import com.practice.repository.StudentRepository;
import com.practice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {

        Student student = mapper.toEntity(dto);
        Student saved = repository.save(student);

        return mapper.toDTO(saved);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public StudentResponseDTO getStudent(Long id) {

        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        return mapper.toDTO(student);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {

        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setName(dto.name());
        student.setEmail(dto.email());

        return mapper.toDTO(repository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found");
        }

        repository.deleteById(id);
    }
}