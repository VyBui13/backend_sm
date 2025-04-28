package com.studentmanagement.staff.mapper;

import com.studentmanagement.staff.dto.StaffDto;
import com.studentmanagement.staff.model.Staff;

public class StaffMapper {
    public static StaffDto toDto(Staff staff) {
        StaffDto dto = new StaffDto();
        dto.setId(staff.getId());
        dto.setEmail(staff.getEmail());
        dto.setFullname(staff.getFullname());
        dto.setSalary(staff.getSalary());
        
        return dto;
    }
    
    public static Staff toEntity(StaffDto staffDto) {
        Staff staff = new Staff();
        staff.setId(staffDto.getId());
        staff.setEmail(staffDto.getEmail());
        staff.setFullname(staffDto.getFullname());
        staff.setSalary(staffDto.getSalary());

        return staff;
    }
}
