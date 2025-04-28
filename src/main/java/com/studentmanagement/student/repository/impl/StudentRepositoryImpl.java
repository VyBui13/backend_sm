package com.studentmanagement.student.repository.impl;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.studentmanagement.staff.mapper.StaffRowMapper;
import com.studentmanagement.student.repository.StudentRepository;

import jakarta.annotation.PostConstruct;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getStudentsProc;

    @PostConstruct
    private void init() {
        getStudentsProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_SEL_PUBLIC_NHANVIEN")
                .declareParameters(
                    new SqlParameter("TENDN", Types.NVARCHAR),
                    new SqlParameter("MK", Types.NVARCHAR)
                )
                .returningResultSet("result", new StaffRowMapper());
    }
}
