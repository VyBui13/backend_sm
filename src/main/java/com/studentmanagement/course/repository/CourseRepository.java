package com.studentmanagement.course.repository;

import java.util.List;

import com.studentmanagement.course.model.CourseDto;

public interface CourseRepository {
    public List<CourseDto> getCourseByStudentId(String studentId, String password);
    public CourseDto updateCourseGradeByStudentId(
        String studentId, 
        String courseId,
        Float grade,
        String staffId,
        String pubKey,
        String password
    );
}