package com.studentmanagement.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.common.response.ApiResponse;
import com.studentmanagement.course.model.CourseDto;
import com.studentmanagement.course.model.UpdateCourseGradeDto;
import com.studentmanagement.course.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseDto>>> getCourseByStudentId(
        @RequestParam(required = true, name = "studentId") String studentId,
        @RequestParam(required = true, name = "password") String password
    ) {
        List<CourseDto> courses = courseService.getCourseByStudentId(studentId, password);

        return ResponseEntity.ok(
            new ApiResponse<List<CourseDto>>(
                "success",
                "Courses retrieved successfully",
                courses
            )
        );
    }

    @PatchMapping()
    public ResponseEntity<ApiResponse<List<CourseDto>>> updateCourseGrades(
        @RequestParam(
            required = true,
            name = "studentId"
        ) String studentId,
        @RequestBody UpdateCourseGradeDto updateCourseGradeDto
    ) {
        List<CourseDto> courses = courseService.updateCourseGradeByStudentId(studentId, updateCourseGradeDto);

        return ResponseEntity.ok(
            new ApiResponse<List<CourseDto>>(
                "success",
                "Courses grade updated successfully",
                courses
            )
        );
    }
}
