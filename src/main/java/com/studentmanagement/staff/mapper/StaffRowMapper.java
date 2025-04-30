package com.studentmanagement.staff.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.studentmanagement.staff.model.Staff;

public class StaffRowMapper implements RowMapper<Staff> {
    @Override
    public Staff mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Staff staff = new Staff();

        staff.setId(rs.getString("MANV"));
        staff.setFullname(rs.getString("HOTEN"));
        staff.setEmail(rs.getString("EMAIL"));
        staff.setSalary(rs.getLong("LUONGCB"));

        return staff;
    }
}
