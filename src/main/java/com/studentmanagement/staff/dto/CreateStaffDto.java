package com.studentmanagement.staff.dto;

import lombok.Data;

@Data
public class CreateStaffDto {
    private String id;
    private String fullname;
    private String email;
    private Long salary;
    private String username;
    private String password;
    private String pub; // Assuming this is a string representation of the public key

    public CreateStaffDto() {
    }

    public CreateStaffDto(
            String id,
            String fullname,
            String email,
            Long salary,
            String username,
            String password,
            String publicKey) {

        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.salary = salary;
        this.username = username;
        this.password = password;
        this.pub = publicKey;
    }
}
