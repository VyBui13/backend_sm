package com.studentmanagement.course.repository;

import java.util.List;

import com.studentmanagement.course.model.Course;
import com.studentmanagement.course.model.CourseDto;

public interface CourseRepository {
	public List<Course> getCourseByStudentId(String studentId);

	public Course updateCourseGradeByStudentId(
			String studentId,
			String courseId,
			byte[] grade);

	public void createCourseGradeByStudentId(
			String studentId,
			String courseId,
			byte[] grade);

	public void createCourse(
			String id,
			String name,
			int credits);

	public List<CourseDto> DEPRECATEDgetCourseByStudentId(String studentId, String password);

	public CourseDto DEPRECATEDupdateCourseGradeByStudentId(
			String studentId,
			String courseId,
			Float grade,
			String staffId,
			String password);
}