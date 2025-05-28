package com.studentmanagement.course.model;

import lombok.Data;

@Data
public class CreateCourseDto {
    String id;
    String name;
    int credits;

    public CreateCourseDto() {
    }

    public CreateCourseDto(String id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }
}
