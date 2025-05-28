package com.studentmanagement.staff.repository;

import com.studentmanagement.staff.model.Staff;

public interface StaffRepository {
    Boolean signIn(String username, byte[] password);

    Staff getStaffInfoByCredentials(String username, byte[] password);

    void createStaff(
            String id,
            String fullname,
            String email,
            byte[] salary,
            String username,
            byte[] password,
            String publicKey);

    // Deprecated methods for backward compatibility

    Boolean DEPRECATEDsignIn(String username, String password);

    Staff DEPRECATEDgetStaffInfoByCredentials(String username, String password);

}