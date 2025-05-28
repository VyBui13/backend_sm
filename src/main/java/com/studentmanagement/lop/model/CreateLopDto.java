package com.studentmanagement.lop.model;

import lombok.Data;

@Data
public class CreateLopDto {
    String id;
    String name;
    String staffId;

    public CreateLopDto() {
    }

    public CreateLopDto(String id, String name, String staffId) {
        this.id = id;
        this.name = name;
        this.staffId = staffId;
    }
}
