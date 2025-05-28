package com.studentmanagement.course.model;

import lombok.Data;

@Data
public class CreateCourseGradeDto {
    private String studentId;
    private String courseId;
    private Float grade;
    private String staffId;

    public CreateCourseGradeDto() {
    }

    public CreateCourseGradeDto(String studentId, String courseId, Float grade, String staffId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
        this.staffId = staffId;
    }
}
