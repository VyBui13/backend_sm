package com.studentmanagement.staff.repository;

import com.studentmanagement.staff.model.Staff;

public interface StaffRepository {
    Boolean signIn(String username, String password);
    Staff getStaffInfoByCredentials(String username, String password);
}
