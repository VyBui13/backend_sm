package com.studentmanagement.course.repository.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.studentmanagement.course.mapper.CourseDtoRowMapper;
import com.studentmanagement.course.model.CourseDto;
import com.studentmanagement.course.repository.CourseRepository;

import jakarta.annotation.PostConstruct;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getCourseByStudentId;
    private SimpleJdbcCall updateCourseGradeByStudentId;

    @PostConstruct
    public void init() {
        getCourseByStudentId = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_GET_ALL_HOCPHAN_WITH_DIEM_BY_SINHVIEN")
                .declareParameters(
                    new SqlParameter("MASV", Types.VARCHAR),
                    new SqlParameter("MK", Types.VARCHAR)
                )
                .returningResultSet("result", new CourseDtoRowMapper());

        updateCourseGradeByStudentId = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_UPDATE_DIEM_BY_SINHVIEN_AND_HOCPHAN")
                .declareParameters(
                    new SqlParameter("MASV", Types.VARCHAR),
                    new SqlParameter("MAHP", Types.VARCHAR),
                    new SqlParameter("DIEMTHI", Types.FLOAT),
                    new SqlParameter("MANV", Types.VARCHAR)
                )
                .returningResultSet("result", new CourseDtoRowMapper());
    }
    

    @Override
    public List<CourseDto> getCourseByStudentId(String studentId, String password) {
        MapSqlParameterSource inParams = new MapSqlParameterSource()
                    .addValue("MASV", studentId)
                    .addValue("MK", password);

        Map<String, Object> out = getCourseByStudentId.execute(inParams);
        Object resultObj = out.get("result");

        if (resultObj instanceof List<?> resultList && !resultList.isEmpty()) {
            List<CourseDto> courses = resultList.stream()
                    .filter(CourseDto.class::isInstance)   
                    .map(CourseDto.class::cast)            
                    .toList();

            return courses;
        }

        return List.of();
    }
    
    @Override
    public CourseDto updateCourseGradeByStudentId(
        String studentId,
        String courseId,
        Float grade,
        String staffId,
        String password
    ) {
        MapSqlParameterSource inParams = new MapSqlParameterSource()
                    .addValue("MASV", studentId)
                    .addValue("MAHP", courseId)
                    .addValue("DIEMTHI", grade)
                    .addValue("MANV", staffId)
                    .addValue("MK", password);

        Map<String, Object> out = updateCourseGradeByStudentId.execute(inParams);
        Object resultObj = out.get("result");

        if (resultObj instanceof List<?> resultList 
            && !resultList.isEmpty() 
            && resultList.get(0) instanceof CourseDto courseDto) {
            
            return courseDto;
        }

        return null;
    }
}
