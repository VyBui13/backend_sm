package com.studentmanagement.lop.repository;

import java.util.List;

import com.studentmanagement.lop.model.LopDto;

public interface LopRepository {
    public List<LopDto> getLopWithStaff();
}
