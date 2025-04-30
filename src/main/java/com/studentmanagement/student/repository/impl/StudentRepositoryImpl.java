package com.studentmanagement.student.repository.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.studentmanagement.student.mapper.StudentRowMapper;
import com.studentmanagement.student.model.Student;
import com.studentmanagement.student.model.UpdateStudentDto;
import com.studentmanagement.student.repository.StudentRepository;

import jakarta.annotation.PostConstruct;


@Repository
public class StudentRepositoryImpl implements StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall getAllStudents;
    private SimpleJdbcCall getAllStudentsByStaffId;
    private SimpleJdbcCall updateStudent;

    @PostConstruct
    public void init() {
        getAllStudents = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_SEL_ALL_PUBLIC_SINHVIEN")
                .returningResultSet("result", new StudentRowMapper());

        getAllStudentsByStaffId = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_SEL_PUBLIC_SINHVIEN_BY_NHANVIEN")
                .declareParameters(
                    new SqlParameter("MANV", Types.VARCHAR)
                )
                .returningResultSet("result", new StudentRowMapper());

        updateStudent = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_UPDATE_SINHVIEN")
                .declareParameters(
                    new SqlParameter("MASV", Types.VARCHAR),
                    new SqlParameter("HOTEN", Types.NVARCHAR),
                    new SqlParameter("NGAYSINH", Types.TIMESTAMP), // !
                    new SqlParameter("DIACHI", Types.NVARCHAR),
                    new SqlParameter("MALOP", Types.VARCHAR),
                    new SqlParameter("MANV", Types.VARCHAR),
                    new SqlParameter("PUBKEY", Types.VARCHAR)
                )
                .returningResultSet("result", new StudentRowMapper());
    }

    @Override
    public List<Student> getAllStudents() {
        Map<String, Object> out = getAllStudents.execute();
        Object resultObj = out.get("result");

        if (resultObj instanceof List<?> resultList && !resultList.isEmpty()) {
            List<Student> students = resultList.stream()
                    .filter(Student.class::isInstance)   
                    .map(Student.class::cast)            
                    .toList();

            return students;
        }

        return List.of();
    }

    @Override
    public List<Student> getAllStudents(String staffId) {
        Map<String, Object> inParams = Map.of(
            "MANV", staffId
        );

        Map<String, Object> out = getAllStudentsByStaffId.execute(inParams);
        Object resultObj = out.get("result");

        if (resultObj instanceof List<?> resultList && !resultList.isEmpty()) {
            List<Student> students = resultList.stream()
                    .filter(Student.class::isInstance)   
                    .map(Student.class::cast)            
                    .toList();

            return students;
        }

        return List.of();
    }

    @Override
    public Student updateStudent(String id, UpdateStudentDto updateStudentDto) {
        MapSqlParameterSource inParams = new MapSqlParameterSource()
                    .addValue("MASV", id)
                    .addValue("HOTEN", updateStudentDto.getFullname())
                    .addValue("NGAYSINH", updateStudentDto.getDob(), Types.TIMESTAMP) // Có thể null
                    .addValue("DIACHI", updateStudentDto.getAddress(), Types.NVARCHAR) // Có thể null
                    .addValue("MALOP", updateStudentDto.getClassId(), Types.NVARCHAR) // Có thể null
                    .addValue("MANV", updateStudentDto.getStaffId())
                    .addValue("PUBKEY", updateStudentDto.getPubKey());

        Map<String, Object> out = updateStudent.execute(inParams);

        Object resultObj = out.get("result");

        if (resultObj instanceof List<?> resultList 
            && !resultList.isEmpty() 
            && resultList.get(0) instanceof Student student) {
            
            return student;
        }

        return null;
    }
}
