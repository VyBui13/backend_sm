package com.studentmanagement.course.service;

import java.util.List;

import com.studentmanagement.course.model.CourseDto;
import com.studentmanagement.course.model.UpdateCourseGradeDto;

public interface CourseService {
    public List<CourseDto> getCourseByStudentId(String studentId, String password);
    public List<CourseDto> updateCourseGradeByStudentId(
        String studentId, 
        UpdateCourseGradeDto updateCourseGradeDto
    );
}
