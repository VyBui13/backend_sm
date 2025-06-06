package com.studentmanagement.student.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UpdateStudentDto {
    private String fullname;
    private Timestamp dob;
    private String address;
    private String staffId;
    private String password;

    public UpdateStudentDto() {
    }

    public UpdateStudentDto(String fullname, Timestamp dob, String address, String staffId, String password) {
        this.fullname = fullname;
        this.dob = dob;
        this.address = address;
        this.staffId = staffId;
        this.password = password;
    }
}
