package com.studentmanagement.course.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.studentmanagement.course.model.Course;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();

        course.setCourseId(rs.getString("MAHP"));
        course.setCourseName(rs.getString("TENHP"));
        course.setCredits(rs.getInt("SOTC"));
        course.setGrade(rs.getBytes("DIEMTHI"));

        return course;
    }
}
