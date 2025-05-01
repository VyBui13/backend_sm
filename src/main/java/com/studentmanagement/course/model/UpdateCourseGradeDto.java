package com.studentmanagement.course.model;

import java.util.Map;

public class UpdateCourseGradeDto {
    private Map<String, Float> updateCourseGradeData;
    private String password;
    private String staffId;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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
