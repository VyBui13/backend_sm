package com.studentmanagement.course.model;

import java.util.Map;

import lombok.Data;

@Data
public class UpdateCourseGradeDto {
    private Map<String, Float> updateCourseGradeData;
    private String password;
    private String staffId;

    public UpdateCourseGradeDto() {
        // Default constructor
    }

    public UpdateCourseGradeDto(Map<String, Float> updateCourseGradeData, String username, String password,
            String staffId) {
        this.updateCourseGradeData = updateCourseGradeData;
        this.password = password;
        this.staffId = staffId;
    }
}
