package com.studentmanagement.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.common.response.ApiResponse;
import com.studentmanagement.course.model.CourseDto;
import com.studentmanagement.course.model.UpdateCourseGradeDto;
import com.studentmanagement.course.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import com.microsoft.sqlserver.jdbc.SQLServerException;

@RestController
@RequestMapping("/courses")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<CourseDto>>> getCourseByStudentId(
			@RequestParam(required = true, name = "studentId") String studentId,
			@RequestParam(required = true, name = "staffId") String staffId,
			@RequestParam(required = true, name = "password") String password) {
		try {
			List<CourseDto> courses = courseService.getCourseByStudentId(studentId, staffId, password);

			return ResponseEntity.ok(
					new ApiResponse<List<CourseDto>>(
							"success",
							"Courses retrieved successfully",
							courses));
		} catch (Exception e) {
			if (e.getCause() instanceof SQLServerException) {

				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
						new ApiResponse<List<CourseDto>>(
								"failed",
								"Unmatched credentials",
								null));
			}

			return ResponseEntity.internalServerError().body(
					new ApiResponse<List<CourseDto>>(
							"failed",
							e.getMessage(),
							null));
		}
	}

	@PatchMapping()
	public ResponseEntity<ApiResponse<List<CourseDto>>> updateCourseGrades(
			@RequestParam(required = true, name = "studentId") String studentId,
			@RequestBody UpdateCourseGradeDto updateCourseGradeDto) {
		try {
			List<CourseDto> courses = courseService.updateCourseGradeByStudentId(studentId,
					updateCourseGradeDto);

			return ResponseEntity.ok(
					new ApiResponse<List<CourseDto>>(
							"success",
							"Courses grade updated successfully",
							courses));
		} catch (Exception e) {
			if (e.getCause() instanceof SQLServerException) {
				SQLServerException sqlEx = (SQLServerException) e.getCause();
				// Lấy thông tin lỗi từ RAISERROR
				String errorMessage = sqlEx.getMessage();

				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
						new ApiResponse<List<CourseDto>>(
								"failed",
								errorMessage,
								null));
			}

			return ResponseEntity.internalServerError().body(
					new ApiResponse<List<CourseDto>>(
							"failed",
							e.getMessage(),
							null));
		}
	}
}
