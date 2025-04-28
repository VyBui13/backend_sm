package com.studentmanagement.staff.dto;

public class StaffSignInDto {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return username + " " + password;
    }
}
