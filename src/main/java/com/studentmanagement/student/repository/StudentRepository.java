package com.studentmanagement.student.repository;

import java.util.List;

import com.studentmanagement.student.model.Student;

public interface StudentRepository {
    public List<Student> getAllStudents();
    public List<Student> getAllStudents(String staffId);
}
