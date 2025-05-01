package com.studentmanagement.lop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.studentmanagement.lop.model.LopDto;

public class LopWithStaffRowMapper implements RowMapper<LopDto> {
    @Override
    public LopDto mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        LopDto lop = new LopDto();

        lop.setClassId(rs.getString("MALOP"));
        lop.setClassName(rs.getString("TENLOP"));
        lop.setStaffId(rs.getString("MANV"));
        lop.setStaffName(rs.getString("HOTEN"));

        return lop;
    }
}
