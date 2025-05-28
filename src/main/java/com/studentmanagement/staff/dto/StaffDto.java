package com.studentmanagement.staff.dto;

public class StaffDto {
    private String id;

    private String fullname;

    private String email;

    private Long salary;

    public StaffDto() {
    }

    public StaffDto(String id, String fullname, Long salary, String email) {
        this.id = id;
        this.fullname = fullname;
        this.salary = salary;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
