package com.studentmanagement.lop.service;

import java.util.List;

import com.studentmanagement.course.model.CreateCourseDto;
import com.studentmanagement.lop.model.LopDto;

public interface LopService {
    public List<LopDto> getLopWithStaff();

    public void createLop(CreateCourseDto createCourseDto);
}
