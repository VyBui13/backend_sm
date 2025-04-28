package com.studentmanagement.staff.repository;

import com.studentmanagement.staff.model.Staff;

public interface StaffRepository {
    Staff getStaffInfoByCredentials(String username, String password);
}
