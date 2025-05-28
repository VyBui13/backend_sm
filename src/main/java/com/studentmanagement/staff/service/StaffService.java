package com.studentmanagement.staff.service;

import java.util.List;

import com.studentmanagement.staff.dto.CreateStaffDto;
import com.studentmanagement.staff.dto.StaffDto;

public interface StaffService {
    public Boolean signIn(String username, String password);

    public StaffDto getStaffInfoByCredentials(String username, String password);

    public StaffDto createStaff(CreateStaffDto createStaffDto);

    public List<StaffDto> getAllStaffs();

    public StaffDto getStaff(String id);

    public StaffDto updateStaff(StaffDto staffDto);

    public StaffDto deleteStaff(String id);

    public Boolean DEPRECATEDsignIn(String username, String password);

    public StaffDto DEPRECATEDgetStaffInfoByCredentials(String username, String password);
}
