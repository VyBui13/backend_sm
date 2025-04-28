package com.studentmanagement.student.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.studentmanagement.student.model.Student;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        
        student.setId(rs.getString("MASV"));
        student.setFullname(rs.getString("HOTEN"));
        student.setDob(rs.getTimestamp("NGAYSINH"));
        student.setAddress(rs.getString("DIACHI"));
        student.setClassId(rs.getString("MALOP"));

        return student;
    }
}
