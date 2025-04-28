package com.studentmanagement.student.repository.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.studentmanagement.student.mapper.StudentRowMapper;
import com.studentmanagement.student.model.Student;
import com.studentmanagement.student.repository.StudentRepository;

import jakarta.annotation.PostConstruct;


@Repository
public class StudentRepositoryImpl implements StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getAllStudents;
    private SimpleJdbcCall getAllStudentsByStaffId;

    @PostConstruct
    public void init() {
        getAllStudents = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_SEL_ALL_PUBLIC_SINHVIEN")
                .returningResultSet("result", new StudentRowMapper());

        getAllStudentsByStaffId = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_SEL_PUBLIC_SINHVIEN_BY_NHANVIEN")
                .declareParameters(
                    new SqlParameter("MANV", Types.NVARCHAR)
                )
                .returningResultSet("result", new StudentRowMapper());
    }

    @Override
    public List<Student> getAllStudents() {
        Map<String, Object> out = getAllStudents.execute();
        Object resultObj = out.get("result");

        if (resultObj instanceof List<?> resultList && !resultList.isEmpty()) {
            List<Student> students = resultList.stream()
                    .filter(Student.class::isInstance)   
                    .map(Student.class::cast)            
                    .toList();

            return students;
        }

        return List.of();
    }

    @Override
    public List<Student> getAllStudents(String staffId) {
        Map<String, Object> inParams = Map.of(
            "MANV", staffId
        );

        Map<String, Object> out = getAllStudentsByStaffId.execute(inParams);
        Object resultObj = out.get("result");

        if (resultObj instanceof List<?> resultList && !resultList.isEmpty()) {
            List<Student> students = resultList.stream()
                    .filter(Student.class::isInstance)   
                    .map(Student.class::cast)            
                    .toList();

            return students;
        }

        return List.of();
    }
}
