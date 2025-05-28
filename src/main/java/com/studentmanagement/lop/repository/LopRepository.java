package com.studentmanagement.lop.repository;

import java.util.List;

import com.studentmanagement.lop.model.LopDto;

public interface LopRepository {
    public List<LopDto> getLopWithStaff();

    public void createLop(String id, String name, String staffId);
}
