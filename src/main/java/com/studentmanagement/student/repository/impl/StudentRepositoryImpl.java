package com.studentmanagement.student.repository.impl;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
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
	private JdbcTemplate sqlServerJdbcTemplate;

	public StudentRepositoryImpl(@Qualifier("sqlServerJdbcTemplate") JdbcTemplate sqlServerJdbcTemplate) {
		this.sqlServerJdbcTemplate = sqlServerJdbcTemplate;
	}

	private SimpleJdbcCall getAllStudents;
	private SimpleJdbcCall getAllStudentsByStaffId;
	private SimpleJdbcCall updateStudent;
	private SimpleJdbcCall createStudent;

	@PostConstruct
	public void init() {
		getAllStudents = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_SEL_ALL_PUBLIC_SINHVIEN")
				.returningResultSet("result", new StudentRowMapper());

		getAllStudentsByStaffId = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_SEL_PUBLIC_SINHVIEN_BY_NHANVIEN")
				.declareParameters(
						new SqlParameter("MANV", Types.VARCHAR))
				.returningResultSet("result", new StudentRowMapper());

		updateStudent = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_UPDATE_SINHVIEN")
				.declareParameters(
						new SqlParameter("MASV", Types.VARCHAR),
						new SqlParameter("HOTEN", Types.NVARCHAR),
						new SqlParameter("NGAYSINH", Types.TIMESTAMP),
						new SqlParameter("DIACHI", Types.NVARCHAR))
				.returningResultSet("result", new StudentRowMapper());

		createStudent = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_INS_PUBLIC_ENCRYPT_SINHVIEN")
				.declareParameters(
						new SqlParameter("MASV", Types.VARCHAR),
						new SqlParameter("HOTEN", Types.NVARCHAR),
						new SqlParameter("NGAYSINH", Types.TIMESTAMP), // !
						new SqlParameter("DIACHI", Types.NVARCHAR),
						new SqlParameter("MALOP", Types.VARCHAR),
						new SqlParameter("TENDN", Types.VARCHAR),
						new SqlParameter("MK", Types.VARBINARY));

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
				"MANV", staffId);

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
				.addValue("MANV", updateStudentDto.getStaffId())
				.addValue("MK", updateStudentDto.getPassword());

		Map<String, Object> out = updateStudent.execute(inParams);

		Object resultObj = out.get("result");

		if (resultObj instanceof List<?> resultList
				&& !resultList.isEmpty()
				&& resultList.get(0) instanceof Student student) {

			return student;
		}

		return null;
	}

	@Override
	public void createStudent(String id, String fullname, Timestamp dob, String address, String classId,
			String username, byte[] password) {
		Map<String, Object> inParams = Map.of(
				"MASV", id,
				"HOTEN", fullname,
				"NGAYSINH", dob,
				"DIACHI", address,
				"MALOP", classId,
				"TENDN", username,
				"MK", password);

		createStudent.execute(inParams);
	}
}
