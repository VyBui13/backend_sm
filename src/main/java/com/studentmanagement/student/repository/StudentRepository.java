package com.studentmanagement.student.repository;

import java.sql.Timestamp;
import java.util.List;

import com.studentmanagement.student.model.Student;
import com.studentmanagement.student.model.UpdateStudentDto;

public interface StudentRepository {
    public List<Student> getAllStudents();

    public List<Student> getAllStudents(String staffId);

    public Student updateStudent(String id, UpdateStudentDto updateStudentDto);

    public void createStudent(
            String id,
            String fullname,
            Timestamp dob,
            String address,
            String classId,
            String username,
            byte[] password);
}
