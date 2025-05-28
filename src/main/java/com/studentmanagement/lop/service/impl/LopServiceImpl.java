package com.studentmanagement.lop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.course.model.CreateCourseDto;
import com.studentmanagement.lop.model.CreateLopDto;
import com.studentmanagement.lop.model.LopDto;
import com.studentmanagement.lop.repository.LopRepository;
import com.studentmanagement.lop.service.LopService;

@Service
public class LopServiceImpl implements LopService {
    @Autowired
    private LopRepository lopRepository;

    @Override
    public List<LopDto> getLopWithStaff() {
        List<LopDto> lops = lopRepository.getLopWithStaff();

        return lops;
    }

    @Override
    public void createLop(CreateCourseDto a) {
        CreateLopDto createLopDto[] = {
                new CreateLopDto("LOP001", "Lớp 12A1", "NV001"),
                new CreateLopDto("LOP002", "Lớp 12A2", "NV002")
        };

        for (CreateLopDto createLop : createLopDto) {
            lopRepository.createLop(createLop.getId(), createLop.getName(), createLop.getStaffId());
        }
    }
}
