package com.studentmanagement.staff.mapper;

import com.studentmanagement.staff.dto.StaffDto;
import com.studentmanagement.staff.model.Staff;

public class StaffMapper {
    public StaffDto toDto(Staff staff) {
        StaffDto dto = new StaffDto();
        dto.setEmail(staff.getEmail());
        dto.setFullname(staff.getFullname());
        dto.setSalary(staff.getSalary());
        dto.setUsername(staff.getUsername());

        return dto;
    }

    public Staff toEntity(StaffDto staffDto) {
        Staff staff = new Staff();
        staff.setEmail(staffDto.getEmail());
        staff.setFullname(staffDto.getFullname());
        staff.setSalary(staffDto.getSalary());
        staff.setUsername(staffDto.getUsername());

        return staff;
    }
}
