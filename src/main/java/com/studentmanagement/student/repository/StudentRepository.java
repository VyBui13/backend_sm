package com.studentmanagement.student.repository;

import java.util.List;

import com.studentmanagement.student.model.Student;
import com.studentmanagement.student.model.UpdateStudentDto;

public interface StudentRepository {
    public List<Student> getAllStudents();
    public List<Student> getAllStudents(String staffId);
    public Student updateStudent(String id, UpdateStudentDto updateStudentDto);
}
