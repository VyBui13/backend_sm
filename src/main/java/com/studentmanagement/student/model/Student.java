package com.studentmanagement.student.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    private String id;

    private String fullname;
    private LocalDateTime dob;
    private String address;
    private String classId;
}
