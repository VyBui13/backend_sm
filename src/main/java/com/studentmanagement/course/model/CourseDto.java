package com.studentmanagement.course.model;

public class CourseDto {
    private String courseId;
    private String courseName;
    private Integer credits;
    private Float grade;

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
    
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Float getGrade() {
        return grade;
    }
    
    public void setGrade(Float grade) {
        this.grade = grade;
    }
}
