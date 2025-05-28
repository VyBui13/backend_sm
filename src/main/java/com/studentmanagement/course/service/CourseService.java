package com.studentmanagement.course.service;

import java.util.List;

import com.studentmanagement.course.model.CourseDto;
import com.studentmanagement.course.model.CreateCourseDto;
import com.studentmanagement.course.model.CreateCourseGradeDto;
import com.studentmanagement.course.model.UpdateCourseGradeDto;

public interface CourseService {
        public List<CourseDto> getCourseByStudentId(String studentId, String staffId, String password);

        public List<CourseDto> updateCourseGradeByStudentId(
                        String studentId,
                        UpdateCourseGradeDto updateCourseGradeDto);

        public void createCourseGrade(CreateCourseGradeDto createCourseDto);

        public void createCourse(CreateCourseDto createCoruseDto);

        // Deprecated methods, kept for backward compatibility

        public List<CourseDto> DEPRECATEDgetCourseByStudentId(String studentId, String password);

        public List<CourseDto> DEPRECATEDupdateCourseGradeByStudentId(
                        String studentId,
                        UpdateCourseGradeDto updateCourseGradeDto);
}
