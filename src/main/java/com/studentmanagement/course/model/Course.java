package com.studentmanagement.course.model;

import lombok.Data;

@Data
public class Course {
    private String courseId;
    private String courseName;
    private Integer credits;
    private byte[] grade;

    public Course() {
    }

    public Course(String courseId, String courseName, Integer credits, byte[] grade) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.grade = grade;
    }
}
