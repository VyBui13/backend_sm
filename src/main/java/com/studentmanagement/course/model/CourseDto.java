package com.studentmanagement.course.model;

import lombok.Data;

@Data
public class CourseDto {
    private String courseId;
    private String courseName;
    private Integer credits;
    private Float grade;

    public CourseDto() {
    }

    public CourseDto(String courseId, String courseName, Integer credits, Float grade) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.grade = grade;
    }
}
