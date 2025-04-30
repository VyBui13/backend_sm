package com.studentmanagement.course.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.studentmanagement.course.model.CourseDto;

public class CourseDtoRowMapper implements RowMapper<CourseDto> {
    @Override
    public CourseDto mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        CourseDto courseDto = new CourseDto();

        courseDto.setCourseId(rs.getString("MAHP"));
        courseDto.setCourseName(rs.getString("TENHP"));
        courseDto.setCredits(rs.getInt("SOTC"));
        courseDto.setGrade(rs.getFloat("DIEMTHI"));

        return courseDto;
    }
}
