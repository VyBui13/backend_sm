package com.studentmanagement.staff.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInRowMapper implements RowMapper<Boolean> {
    @Override
    public Boolean mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Integer result = rs.getInt(1);
        return result == 1;
    }
}