package com.studentmanagement.staff.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.staff.dto.StaffDto;
import com.studentmanagement.staff.mapper.StaffMapper;
import com.studentmanagement.staff.model.Staff;
import com.studentmanagement.staff.repository.StaffRepository;
import com.studentmanagement.staff.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffRepository staffRepository;
    
    @Override
    public StaffDto getStaffInfoByCredentials(String username, String password) {
        Staff foundStaff = staffRepository.getStaffInfoByCredentials(username, password);

        if (foundStaff == null) {
            return null;
        }

        return StaffMapper.toDto(foundStaff);
    }

    public List<StaffDto> getAllStaffs() {
        return new ArrayList<StaffDto>();
    }

    public StaffDto getStaff(String id) {
        return new StaffDto();
    }

    public StaffDto createStaff(StaffDto staffDto) {
        return new StaffDto();
    }

    public StaffDto updateStaff(StaffDto staffDto) {
        return new StaffDto();
    }
    
    public StaffDto deleteStaff(String id) {
        return new StaffDto();
    }
}
