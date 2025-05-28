package com.studentmanagement.course.repository.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.studentmanagement.course.mapper.CourseDtoRowMapper;
import com.studentmanagement.course.mapper.CourseRowMapper;
import com.studentmanagement.course.model.Course;
import com.studentmanagement.course.model.CourseDto;
import com.studentmanagement.course.repository.CourseRepository;

import jakarta.annotation.PostConstruct;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
	private JdbcTemplate sqlServerJdbcTemplate;

	public CourseRepositoryImpl(@Qualifier("sqlServerJdbcTemplate") JdbcTemplate sqlServerJdbcTemplate) {
		this.sqlServerJdbcTemplate = sqlServerJdbcTemplate;
	}

	private SimpleJdbcCall createCourseGradeByStudentId;
	private SimpleJdbcCall getCourseByStudentId;
	private SimpleJdbcCall updateCourseGradeByStudentId;
	private SimpleJdbcCall createCourse;

	private SimpleJdbcCall DEPRECATEDgetCourseByStudentId;
	private SimpleJdbcCall DEPRECATEDupdateCourseGradeByStudentId;

	@PostConstruct
	public void init() {
		getCourseByStudentId = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_GET_ALL_ENCRYPT_HOCPHAN_WITH_DIEM_BY_SINHVIEN")
				.declareParameters(
						new SqlParameter("MASV", Types.VARCHAR))
				.returningResultSet("result", new CourseRowMapper());

		updateCourseGradeByStudentId = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_UPDATE_ENCRYPT_DIEM_BY_SINHVIEN_AND_HOCPHAN")
				.declareParameters(
						new SqlParameter("MASV", Types.VARCHAR),
						new SqlParameter("MAHP", Types.VARCHAR),
						new SqlParameter("DIEMTHI", Types.VARBINARY))
				.returningResultSet("result", new CourseRowMapper());

		createCourseGradeByStudentId = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_INS_ENCRYPT_DIEM_BY_SINHVIEN_AND_HOCPHAN")
				.declareParameters(
						new SqlParameter("MASV", Types.VARCHAR),
						new SqlParameter("MAHP", Types.VARCHAR),
						new SqlParameter("DIEMTHI", Types.VARBINARY))
				.returningResultSet("result", new CourseRowMapper());

		createCourse = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_INS_HOCPHAN")
				.declareParameters(
						new SqlParameter("MAHP", Types.VARCHAR),
						new SqlParameter("TENHP", Types.NVARCHAR),
						new SqlParameter("SOTC", Types.INTEGER))
				.returningResultSet("result", new CourseRowMapper());

		// ---------------------------------------------------------------------------------

		DEPRECATEDgetCourseByStudentId = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_GET_ALL_HOCPHAN_WITH_DIEM_BY_SINHVIEN")
				.declareParameters(
						new SqlParameter("MASV", Types.VARCHAR),
						new SqlParameter("MK", Types.VARCHAR))
				.returningResultSet("result", new CourseDtoRowMapper());

		DEPRECATEDupdateCourseGradeByStudentId = new SimpleJdbcCall(sqlServerJdbcTemplate)
				.withProcedureName("SP_UPDATE_DIEM_BY_SINHVIEN_AND_HOCPHAN")
				.declareParameters(
						new SqlParameter("MASV", Types.VARCHAR),
						new SqlParameter("MAHP", Types.VARCHAR),
						new SqlParameter("DIEMTHI", Types.FLOAT),
						new SqlParameter("MANV", Types.VARCHAR))
				.returningResultSet("result", new CourseDtoRowMapper());

	}

	@Override
	public List<Course> getCourseByStudentId(String studentId) {
		MapSqlParameterSource inParams = new MapSqlParameterSource()
				.addValue("MASV", studentId);

		Map<String, Object> out = getCourseByStudentId.execute(inParams);
		Object resultObj = out.get("result");

		if (resultObj instanceof List<?> resultList && !resultList.isEmpty()) {
			List<Course> courses = resultList.stream()
					.filter(Course.class::isInstance)
					.map(Course.class::cast)
					.toList();

			return courses;
		}

		return List.of();
	}

	@Override
	public Course updateCourseGradeByStudentId(
			String studentId,
			String courseId,
			byte[] grade) {

		MapSqlParameterSource inParams = new MapSqlParameterSource()
				.addValue("MASV", studentId)
				.addValue("MAHP", courseId)
				.addValue("DIEMTHI", grade);

		Map<String, Object> out = updateCourseGradeByStudentId.execute(inParams);
		Object resultObj = out.get("result");

		if (resultObj instanceof List<?> resultList
				&& !resultList.isEmpty()
				&& resultList.get(0) instanceof Course course) {

			return course;
		}

		return null;
	}

	@Override
	public void createCourseGradeByStudentId(
			String studentId,
			String courseId,
			byte[] grade) {

		MapSqlParameterSource inParams = new MapSqlParameterSource()
				.addValue("MASV", studentId)
				.addValue("MAHP", courseId)
				.addValue("DIEMTHI", grade);

		createCourseGradeByStudentId.execute(inParams);
	}

	@Override
	public void createCourse(String id, String name, int credits) {
		MapSqlParameterSource inParams = new MapSqlParameterSource()
				.addValue("MAHP", id)
				.addValue("TENHP", name)
				.addValue("SOTC", credits);

		createCourse.execute(inParams);
	}

	@Override
	public List<CourseDto> DEPRECATEDgetCourseByStudentId(String studentId, String password) {
		MapSqlParameterSource inParams = new MapSqlParameterSource()
				.addValue("MASV", studentId)
				.addValue("MK", password);

		Map<String, Object> out = DEPRECATEDgetCourseByStudentId.execute(inParams);
		Object resultObj = out.get("result");

		if (resultObj instanceof List<?> resultList && !resultList.isEmpty()) {
			List<CourseDto> courses = resultList.stream()
					.filter(CourseDto.class::isInstance)
					.map(CourseDto.class::cast)
					.toList();

			return courses;
		}

		return List.of();
	}

	@Override
	public CourseDto DEPRECATEDupdateCourseGradeByStudentId(
			String studentId,
			String courseId,
			Float grade,
			String staffId,
			String password) {
		MapSqlParameterSource inParams = new MapSqlParameterSource()
				.addValue("MASV", studentId)
				.addValue("MAHP", courseId)
				.addValue("DIEMTHI", grade)
				.addValue("MANV", staffId)
				.addValue("MK", password);

		Map<String, Object> out = DEPRECATEDupdateCourseGradeByStudentId.execute(inParams);
		Object resultObj = out.get("result");

		if (resultObj instanceof List<?> resultList
				&& !resultList.isEmpty()
				&& resultList.get(0) instanceof CourseDto courseDto) {

			return courseDto;
		}

		return null;
	}
}
