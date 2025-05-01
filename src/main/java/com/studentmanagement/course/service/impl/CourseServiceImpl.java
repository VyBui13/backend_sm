package com.studentmanagement.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.course.model.CourseDto;
import com.studentmanagement.course.model.UpdateCourseGradeDto;
import com.studentmanagement.course.repository.CourseRepository;
import com.studentmanagement.course.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseDto> getCourseByStudentId(String studentId, String password) {
        List<CourseDto> courses = courseRepository.getCourseByStudentId(studentId, password);

        return courses;
    }
    
    @Override
    public List<CourseDto> updateCourseGradeByStudentId(
        String studentId, 
        UpdateCourseGradeDto updateCourseGradeDto
    ) {
        final String staffId = updateCourseGradeDto.getStaffId();
        final String pubKey = updateCourseGradeDto.getPubKey();
        final String password = updateCourseGradeDto.getPassword();

        updateCourseGradeDto
            .getUpdateCourseGradeData()
            .forEach((courseId, grade) -> {
                courseRepository.updateCourseGradeByStudentId(
                    studentId,
                    courseId,
                    grade,
                    staffId,
                    pubKey,
                    password
                );
            });

        return getCourseByStudentId(studentId, password);
    }
}
