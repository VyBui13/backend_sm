package com.studentmanagement.course.model;

import java.util.Map;

public class UpdateCourseGradeDto {
    private Map<String, Float> updateCourseGradeData;
    private String password;
    private String staffId;
    private String pubKey;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Float> getUpdateCourseGradeData() {
        return updateCourseGradeData;
    }
    
    public void setUpdateCourseGradeData(Map<String, Float> updateCourseGradeData) {
        this.updateCourseGradeData = updateCourseGradeData;
    }
}
