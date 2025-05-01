package com.studentmanagement.staff.service;

import java.util.List;

import com.studentmanagement.staff.dto.StaffDto;

public interface StaffService {
    public Boolean signIn(String username, String password);
    public StaffDto getStaffInfoByCredentials(String username, String password);

    public List<StaffDto> getAllStaffs();
    public StaffDto getStaff(String id);
    public StaffDto createStaff(StaffDto staffDto);
    public StaffDto updateStaff(StaffDto staffDto);
    public StaffDto deleteStaff(String id);
}
