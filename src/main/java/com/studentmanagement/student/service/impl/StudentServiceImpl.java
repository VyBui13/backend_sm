package com.studentmanagement.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.student.model.Student;
import com.studentmanagement.student.model.UpdateStudentDto;
import com.studentmanagement.student.repository.StudentRepository;
import com.studentmanagement.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    
    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.getAllStudents();

        return students;
    }
    
    @Override
    public List<Student> getAllStudents(String staffId) {
        List<Student> students = studentRepository.getAllStudents(staffId);

        return students;
    }

    @Override
    public Student updateStudent(String id, UpdateStudentDto updateStudentDto) {
        Student updatedStudent = studentRepository.updateStudent(id, updateStudentDto);

        return updatedStudent;
    }
}
