package com.studentmanagement.student.model;

import java.sql.Timestamp;

public class UpdateStudentDto {
    private String fullname; // Có thể null 
    private Timestamp dob; // Có thể null
    private String address; // Có thể null
    private String classId; // Có thể null
    private String staffId;
    private String pubKey;
    
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public Timestamp getDob() {
        return dob;
    }
    public void setDob(Timestamp dob) {
        this.dob = dob;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getClassId() {
        return classId;
    }
    public void setClassId(String classId) {
        this.classId = classId;
    }
    public String getStaffId() {
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    public String getPubKey() {
        return pubKey;
    }
    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }
}
