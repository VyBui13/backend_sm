package com.studentmanagement.student.service;

import java.util.List;

import com.studentmanagement.student.model.Student;
import com.studentmanagement.student.model.UpdateStudentDto;

public interface StudentService {
    public List<Student> getAllStudents();
    public List<Student> getAllStudents(String staffId);
    public Student updateStudent(String id, UpdateStudentDto updateStudentDto);
}
