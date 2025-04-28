package com.studentmanagement.student.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.common.response.ApiResponse;
import com.studentmanagement.student.model.Student;
import com.studentmanagement.student.service.StudentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    
}
