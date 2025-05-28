package com.studentmanagement.student.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CreateStudentDto {
    private String id;
    private String fullname;
    private Timestamp dob;
    private String address;
    private String staffId;
    private String username;
    private String password;

    public CreateStudentDto() {
    }

    public CreateStudentDto(
            String id,
            String fullname,
            Timestamp dob,
            String address,
            String staffId,
            String username,
            String password) {

        this.id = id;
        this.fullname = fullname;
        this.dob = dob;
        this.address = address;
        this.staffId = staffId;
        this.username = username;
        this.password = password;
    }
}
