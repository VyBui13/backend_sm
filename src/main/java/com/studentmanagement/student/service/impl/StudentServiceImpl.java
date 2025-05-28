package com.studentmanagement.student.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.studentmanagement.student.model.CreateStudentDto;
import com.studentmanagement.student.model.Student;
import com.studentmanagement.student.model.UpdateStudentDto;
import com.studentmanagement.student.repository.StudentRepository;
import com.studentmanagement.student.service.StudentService;
import com.studentmanagement.util.CryptoUtil;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final CryptoUtil cryptoUtil;

	public StudentServiceImpl(
			StudentRepository studentRepository,
			CryptoUtil cryptoUtil) {
		this.studentRepository = studentRepository;
		this.cryptoUtil = cryptoUtil;
	}

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

	@Override
	public void createStudent(CreateStudentDto a) {
		CreateStudentDto createStudentDto[] = {
				new CreateStudentDto("SV001", "Nguyễn Văn Hùng",
						Timestamp.valueOf("2003-01-15 00:00:00"), "Hà Nội",
						"LOP001", "sv_hung", "sv123"),
				new CreateStudentDto("SV002", "Trần Thị Mai", Timestamp.valueOf("2003-02-20 00:00:00"),
						"Hà Nội",
						"LOP001", "sv_mai", "sv123"),
				new CreateStudentDto("SV003", "Lê Văn Nam", Timestamp.valueOf("2003-03-10 00:00:00"),
						"Hà Nội",
						"LOP001", "sv_nam", "sv123"),
				new CreateStudentDto("SV004", "Phạm Thị Lan", Timestamp.valueOf("2003-04-05 00:00:00"),
						"Hà Nội",
						"LOP001", "sv_lan", "sv123"),
				new CreateStudentDto("SV005", "Hoàng Văn Tùng",
						Timestamp.valueOf("2003-05-12 00:00:00"), "Hà Nội",
						"LOP001", "sv_tung", "sv123"),
				new CreateStudentDto("SV006", "Ngô Thị Hà", Timestamp.valueOf("2003-06-18 00:00:00"),
						"Hà Nội",
						"LOP001", "sv_ha", "sv123"),
				new CreateStudentDto("SV007", "Vũ Văn Long", Timestamp.valueOf("2003-07-22 00:00:00"),
						"Hà Nội",
						"LOP001", "sv_long", "sv123"),
				new CreateStudentDto("SV008", "Đỗ Thị Thảo", Timestamp.valueOf("2003-08-30 00:00:00"),
						"TP.HCM",
						"LOP002", "sv_thao", "sv123"),
				new CreateStudentDto("SV009", "Bùi Văn Khoa", Timestamp.valueOf("2003-09-14 00:00:00"),
						"TP.HCM",
						"LOP002", "sv_khoa", "sv123"),
				new CreateStudentDto("SV010", "Nguyễn Thị Linh",
						Timestamp.valueOf("2003-10-25 00:00:00"), "TP.HCM",
						"LOP002", "sv_linh", "sv123"),
				new CreateStudentDto("SV011", "Trần Văn Dũng", Timestamp.valueOf("2003-11-03 00:00:00"),
						"TP.HCM",
						"LOP002", "sv_dung", "sv123"),
				new CreateStudentDto("SV012", "Lê Thị Hương", Timestamp.valueOf("2003-12-12 00:00:00"),
						"TP.HCM",
						"LOP002", "sv_huong", "sv123")
		};

		for (CreateStudentDto createStudent : createStudentDto) {
			byte[] hashedPassword = cryptoUtil.hash(createStudent.getPassword());

			studentRepository.createStudent(
					createStudent.getId(),
					createStudent.getFullname(),
					createStudent.getDob(),
					createStudent.getAddress(),
					createStudent.getStaffId(),
					createStudent.getUsername(),
					hashedPassword);
		}
	}
}
