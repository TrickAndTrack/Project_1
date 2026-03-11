package com.practice.controller;

import com.practice.dto.StudentRequestDTO;
import com.practice.dto.StudentResponseDTO;
import com.practice.service.StudentService;
import com.practice.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;


    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponseDTO>> createStudent(
            @RequestBody StudentRequestDTO dto) {

        StudentResponseDTO student = service.createStudent(dto);

        ApiResponse<StudentResponseDTO> response =
                ApiResponse.<StudentResponseDTO>builder()
                        .success(true)
                        .message("Student created successfully")
                        .data(student)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<StudentResponseDTO> getAll() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponseDTO get(@PathVariable Long id) {
        return service.getStudent(id);
    }

    @PutMapping("/{id}")
    public StudentResponseDTO update(@PathVariable Long id,
                                     @RequestBody StudentRequestDTO dto) {
        return service.updateStudent(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteStudent(id);
    }
}
