package com.studentmanagement.lop.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.studentmanagement.lop.mapper.LopWithStaffRowMapper;
import com.studentmanagement.lop.model.LopDto;
import com.studentmanagement.lop.repository.LopRepository;

import jakarta.annotation.PostConstruct;

@Repository
public class LopRepositoryImpl implements LopRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getLopWithStaff;

    @PostConstruct
    private void init() {
        getLopWithStaff = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_GET_ALL_LOP")
                .returningResultSet("result", new LopWithStaffRowMapper());
    }

    @Override
    public List<LopDto> getLopWithStaff() {
        Map<String, Object> out = getLopWithStaff.execute();
        Object resultObj = out.get("result");

        if (resultObj instanceof List<?> resultList && !resultList.isEmpty()) {
            List<LopDto> lops = resultList.stream()
                    .filter(LopDto.class::isInstance)   
                    .map(LopDto.class::cast)            
                    .toList();

            return lops;
        }

        return List.of();
    }
}
