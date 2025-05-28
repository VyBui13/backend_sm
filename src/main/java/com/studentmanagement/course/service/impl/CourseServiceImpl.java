package com.studentmanagement.course.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.List;

import org.springframework.stereotype.Service;

import com.studentmanagement.course.model.Course;
import com.studentmanagement.course.model.CourseDto;
import com.studentmanagement.course.model.CreateCourseDto;
import com.studentmanagement.course.model.CreateCourseGradeDto;
import com.studentmanagement.course.model.UpdateCourseGradeDto;
import com.studentmanagement.course.repository.CourseRepository;
import com.studentmanagement.course.service.CourseService;
import com.studentmanagement.key.repository.KeyRepository;
import com.studentmanagement.staff.dto.StaffDto;
import com.studentmanagement.staff.service.StaffService;
import com.studentmanagement.util.CryptoUtil;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final KeyRepository keyRepository;
    private final CryptoUtil cryptoUtil;

    public CourseServiceImpl(
            CourseRepository courseRepository,
            KeyRepository keyRepository,
            CryptoUtil cryptoUtil,
            StaffService staffService) {
        this.courseRepository = courseRepository;
        this.keyRepository = keyRepository;
        this.cryptoUtil = cryptoUtil;
    }

    @Override
    public void createCourseGrade(CreateCourseGradeDto createCourseDto) {
        CreateCourseGradeDto createCourseDtos[] = {
                new CreateCourseGradeDto("SV001", "HP001", 8.5f, "NV001"),
                new CreateCourseGradeDto("SV001", "HP002", 7.0f, "NV001"),
                new CreateCourseGradeDto("SV001", "HP004", 9.0f, "NV001"),
                new CreateCourseGradeDto("SV001", "HP005", 6.5f, "NV001"),
                new CreateCourseGradeDto("SV002", "HP001", 7.5f, "NV001"),
                new CreateCourseGradeDto("SV002", "HP003", 8.0f, "NV001"),
                new CreateCourseGradeDto("SV002", "HP004", 6.0f, "NV001"),
                new CreateCourseGradeDto("SV002", "HP006", 9.5f, "NV001"),
                new CreateCourseGradeDto("SV003", "HP002", 6.5f, "NV001"),
                new CreateCourseGradeDto("SV003", "HP003", 7.0f, "NV001"),
                new CreateCourseGradeDto("SV003", "HP005", 8.0f, "NV001"),
                new CreateCourseGradeDto("SV003", "HP007", 7.5f, "NV001"),
                new CreateCourseGradeDto("SV004", "HP001", 9.0f, "NV001"),
                new CreateCourseGradeDto("SV004", "HP004", 8.5f, "NV001"),
                new CreateCourseGradeDto("SV004", "HP006", 7.0f, "NV001"),
                new CreateCourseGradeDto("SV004", "HP008", 8.0f, "NV001"),
                new CreateCourseGradeDto("SV005", "HP002", 7.5f, "NV001"),
                new CreateCourseGradeDto("SV005", "HP005", 6.0f, "NV001"),
                new CreateCourseGradeDto("SV005", "HP007", 8.5f, "NV001"),
                new CreateCourseGradeDto("SV005", "HP008", 9.0f, "NV001"),
                new CreateCourseGradeDto("SV006", "HP001", 6.5f, "NV001"),
                new CreateCourseGradeDto("SV006", "HP003", 7.0f, "NV001"),
                new CreateCourseGradeDto("SV006", "HP004", 8.0f, "NV001"),
                new CreateCourseGradeDto("SV006", "HP006", 7.5f, "NV001"),
                new CreateCourseGradeDto("SV007", "HP002", 8.0f, "NV001"),
                new CreateCourseGradeDto("SV007", "HP005", 9.0f, "NV001"),
                new CreateCourseGradeDto("SV007", "HP007", 6.5f, "NV001"),
                new CreateCourseGradeDto("SV007", "HP008", 7.0f, "NV001"),
                new CreateCourseGradeDto("SV008", "HP001", 7.5f, "NV002"),
                new CreateCourseGradeDto("SV008", "HP003", 8.0f, "NV002"),
                new CreateCourseGradeDto("SV008", "HP005", 6.5f, "NV002"),
                new CreateCourseGradeDto("SV008", "HP007", 9.0f, "NV002"),
                new CreateCourseGradeDto("SV009", "HP002", 8.5f, "NV002"),
                new CreateCourseGradeDto("SV009", "HP004", 7.0f, "NV002"),
                new CreateCourseGradeDto("SV009", "HP006", 6.0f, "NV002"),
                new CreateCourseGradeDto("SV009", "HP008", 7.5f, "NV002"),
                new CreateCourseGradeDto("SV010", "HP001", 6.5f, "NV002"),
                new CreateCourseGradeDto("SV010", "HP003", 7.0f, "NV002"),
                new CreateCourseGradeDto("SV010", "HP005", 8.0f, "NV002"),
                new CreateCourseGradeDto("SV010", "HP007", 9.5f, "NV002"),
                new CreateCourseGradeDto("SV011", "HP002", 7.0f, "NV002"),
                new CreateCourseGradeDto("SV011", "HP004", 8.5f, "NV002"),
                new CreateCourseGradeDto("SV011", "HP006", 6.5f, "NV002"),
                new CreateCourseGradeDto("SV011", "HP008", 7.0f, "NV002"),
                new CreateCourseGradeDto("SV012", "HP001", 8.0f, "NV002"),
                new CreateCourseGradeDto("SV012", "HP003", 9.0f, "NV002"),
                new CreateCourseGradeDto("SV012", "HP005", 6.0f, "NV002"),
                new CreateCourseGradeDto("SV012", "HP007", 7.5f, "NV002")
        };

        for (CreateCourseGradeDto createCourseGradeDto : createCourseDtos) {
            byte[] encryptedGrade = cryptoUtil.encrypt(
                    Float.toString(createCourseGradeDto.getGrade()),
                    keyRepository.getPublicKey(createCourseGradeDto.getStaffId()));

            courseRepository.createCourseGradeByStudentId(createCourseGradeDto.getStudentId(),
                    createCourseGradeDto.getCourseId(), encryptedGrade);
        }

    }

    @Override
    public List<CourseDto> getCourseByStudentId(String studentId, String staffId, String password) {
        List<Course> courses = courseRepository.getCourseByStudentId(studentId);
        // doing some decryption here

        PrivateKey privateKey = keyRepository.getPrivateKey(staffId);

        List<CourseDto> courseDtos = courses.stream()
                .map(course -> {
                    CourseDto result = new CourseDto(course.getCourseId(),
                            course.getCourseName(),
                            course.getCredits(),
                            0f);

                    if (course.getGrade() == null || course.getGrade().length == 0) {
                        return result;
                    }

                    byte[] decryptedGrade = cryptoUtil.decrypt(
                            course.getGrade(),
                            privateKey);

                    String gradeString = new String(decryptedGrade, StandardCharsets.UTF_16LE);

                    Float grade = Float.parseFloat(gradeString);

                    return new CourseDto(
                            course.getCourseId(),
                            course.getCourseName(),
                            course.getCredits(),
                            grade);
                })
                .toList();

        return courseDtos;
    }

    @Override
    public List<CourseDto> updateCourseGradeByStudentId(
            String studentId,
            UpdateCourseGradeDto updateCourseGradeDto) {
        final String password = updateCourseGradeDto.getPassword();
        final String staffId = updateCourseGradeDto.getStaffId();

        if (!updateCourseGradeDto.getUpdateCourseGradeData().isEmpty()) {
            updateCourseGradeDto
                    .getUpdateCourseGradeData()
                    .forEach((courseId, grade) -> {
                        // doing some encryption here

                        byte[] encryptedGrade = cryptoUtil.encrypt(
                                Float.toString(grade),
                                keyRepository.getPublicKey(updateCourseGradeDto.getStaffId()));

                        courseRepository.updateCourseGradeByStudentId(
                                studentId,
                                courseId,
                                encryptedGrade);
                    });
        }

        return getCourseByStudentId(studentId, staffId, password);
    }

    @Override
    public void createCourse(CreateCourseDto a) {
        CreateCourseDto createCourseDtos[] = {
                new CreateCourseDto("HP001", "Toán Cao Cấp", 3),
                new CreateCourseDto("HP002", "Vật Lý Đại Cương", 3),
                new CreateCourseDto("HP003", "Hóa Học Cơ Bản", 3),
                new CreateCourseDto("HP004", "Lập Trình Cơ Bản", 4),
                new CreateCourseDto("HP005", "Anh Văn 1", 2),
                new CreateCourseDto("HP006", "Kỹ Năng Mềm", 2),
                new CreateCourseDto("HP007", "Tư Tưởng Hồ Chí Minh", 2),
                new CreateCourseDto("HP008", "Giáo Dục Thể Chất", 1)
        };
        ;

        for (CreateCourseDto createCourseDto : createCourseDtos) {
            courseRepository.createCourse(
                    createCourseDto.getId(),
                    createCourseDto.getName(),
                    createCourseDto.getCredits());
        }
    }

    @Override
    public List<CourseDto> DEPRECATEDgetCourseByStudentId(String studentId, String password) {
        List<CourseDto> courses = courseRepository.DEPRECATEDgetCourseByStudentId(studentId, password);

        return courses;
    }

    @Override
    public List<CourseDto> DEPRECATEDupdateCourseGradeByStudentId(
            String studentId,
            UpdateCourseGradeDto updateCourseGradeDto) {
        final String staffId = updateCourseGradeDto.getStaffId();
        final String password = updateCourseGradeDto.getPassword();

        if (!updateCourseGradeDto.getUpdateCourseGradeData().isEmpty()) {
            updateCourseGradeDto
                    .getUpdateCourseGradeData()
                    .forEach((courseId, grade) -> {
                        courseRepository.DEPRECATEDupdateCourseGradeByStudentId(
                                studentId,
                                courseId,
                                grade,
                                staffId,
                                password);
                    });
        }

        return DEPRECATEDgetCourseByStudentId(studentId, password);
    }
}
