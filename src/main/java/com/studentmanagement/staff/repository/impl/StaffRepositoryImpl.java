package com.studentmanagement.staff.repository.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.studentmanagement.staff.mapper.SignInRowMapper;
import com.studentmanagement.staff.mapper.StaffRowMapper;
import com.studentmanagement.staff.model.Staff;
import com.studentmanagement.staff.repository.StaffRepository;

import jakarta.annotation.PostConstruct;

@Repository
public class StaffRepositoryImpl implements StaffRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall signInStaff;
    private SimpleJdbcCall getStaffInfoProc;

    @PostConstruct
    private void init() {
        signInStaff = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_SIGN_IN_NHANVIEN")
                .declareParameters(
                    new SqlParameter("TENDN", Types.NVARCHAR),
                    new SqlParameter("MK", Types.NVARCHAR)
                )
                .returningResultSet("result", new SignInRowMapper());
        
        getStaffInfoProc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_SEL_PUBLIC_NHANVIEN")
                .declareParameters(
                    new SqlParameter("TENDN", Types.NVARCHAR),
                    new SqlParameter("MK", Types.NVARCHAR)
                )
                .returningResultSet("result", new StaffRowMapper());
    }

    @Override
    public Boolean signIn(String username, String password) {
        Map<String, Object> inParams = Map.of(
            "TENDN", username,
            "MK", password
        );

        Map<String, Object> out = signInStaff.execute(inParams);

        Object resultObj = out.get("result");

        if (resultObj instanceof List<?> resultList 
            && !resultList.isEmpty() 
            && resultList.get(0) instanceof Boolean result) {
            
            return result;
        }

        return null;
    }

    @Override
    public Staff getStaffInfoByCredentials(String username, String password) {
        Map<String, Object> inParams = Map.of(
            "TENDN", username,
            "MK", password
        );

        Map<String, Object> out = getStaffInfoProc.execute(inParams);

        Object resultObj = out.get("result");

        if (resultObj instanceof List<?> resultList 
            && !resultList.isEmpty() 
            && resultList.get(0) instanceof Staff staff) {
            
            return staff;
        }

        return null;
    }
}
