package com.studentmanagement.student.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.common.response.ApiResponse;
import com.studentmanagement.student.model.Student;
import com.studentmanagement.student.model.UpdateStudentDto;
import com.studentmanagement.student.service.StudentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(
        @RequestParam(required = false, name = "staffId") String staffId
    ) {
        if (staffId != null) {
            return ResponseEntity.ok(
                new ApiResponse<>(
                    "success", 
                    "Students with staffId retrieved successfully", 
                    studentService.getAllStudents(staffId)
                )
            );
        }

        return ResponseEntity.ok(
            new ApiResponse<>(
                "success", 
                "Students retrieved successfully", 
                studentService.getAllStudents()
            )
        ); 
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(
        @PathVariable String id,
        @RequestBody UpdateStudentDto updateStudentDto
    ) {
        Student updatedStudent = studentService.updateStudent(id, updateStudentDto);

        return ResponseEntity.ok(
            new ApiResponse<>(
                "success", 
                "Students updated successfully", 
                updatedStudent
            )
        );
    }
}
