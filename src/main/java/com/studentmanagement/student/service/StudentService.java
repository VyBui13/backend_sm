package com.studentmanagement.student.service;

import java.util.List;

import com.studentmanagement.student.model.Student;

public interface StudentService {
    public List<Student> getAllStudents();
    public List<Student> getAllStudents(String staffId);
}
