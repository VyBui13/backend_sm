package com.studentmanagement.datapump.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.common.response.ApiResponse;
import com.studentmanagement.course.model.CreateCourseDto;
import com.studentmanagement.course.model.CreateCourseGradeDto;
import com.studentmanagement.course.service.CourseService;
import com.studentmanagement.lop.service.LopService;
import com.studentmanagement.staff.dto.CreateStaffDto;
import com.studentmanagement.staff.service.StaffService;
import com.studentmanagement.student.model.CreateStudentDto;
import com.studentmanagement.student.service.StudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/datapump")
public class DataPumpController {
    private final StaffService staffService;
    private final LopService lopService;
    private final StudentService studentService;
    private final CourseService courseService;

    public DataPumpController(
            StaffService staffService,
            LopService lopService,
            StudentService studentService,
            CourseService courseService) {
        this.staffService = staffService;
        this.lopService = lopService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<String>> testEndpoint() {

        staffService.createStaff(new CreateStaffDto());
        lopService.createLop(new CreateCourseDto());
        studentService.createStudent(new CreateStudentDto());
        courseService.createCourse(new CreateCourseDto());
        courseService.createCourseGrade(new CreateCourseGradeDto());

        return ResponseEntity.ok(new ApiResponse<>(
                "success",
                "Test endpoint is working",
                "Test successful"));
    }
}
