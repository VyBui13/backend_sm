package com.studentmanagement.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.common.response.ApiResponse;
import com.studentmanagement.staff.dto.StaffDto;
import com.studentmanagement.staff.dto.StaffSignInDto;
import com.studentmanagement.staff.service.StaffService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/staffs")
public class StaffController {

	@Autowired
	private StaffService staffService;

	@PostMapping("/sign-in")
	public ResponseEntity<ApiResponse<StaffDto>> signInStaff(@RequestBody StaffSignInDto staffSignInDto) {
		Boolean isAuthorized = staffService.signIn(staffSignInDto.getUsername(), staffSignInDto.getPassword());

		if (!isAuthorized) {
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(
							new ApiResponse<>(
									"failed",
									"Signed in unsuccessfully",
									null));
		}

		StaffDto foundStaff = staffService.getStaffInfoByCredentials(
				staffSignInDto.getUsername(),
				staffSignInDto.getPassword());

		return ResponseEntity.ok(new ApiResponse<>("success", "Signed in successfully", foundStaff));
	}

	@PostMapping("deprecated/sign-in")
	public ResponseEntity<ApiResponse<StaffDto>> DEPRECATEDsignInStaff(@RequestBody StaffSignInDto staffSignInDto) {
		Boolean isAuthorized = staffService.DEPRECATEDsignIn(staffSignInDto.getUsername(),
				staffSignInDto.getPassword());

		if (!isAuthorized) {
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(
							new ApiResponse<>(
									"failed",
									"Signed in unsuccessfully",
									null));
		}

		StaffDto foundStaff = staffService.DEPRECATEDgetStaffInfoByCredentials(staffSignInDto.getUsername(),
				staffSignInDto.getPassword());

		return ResponseEntity.ok(new ApiResponse<>("success", "Signed in successfully", foundStaff));
	}

	// @GetMapping("/{id}")
	// public ResponseEntity<ApiResponse<StaffDto>> getStaff(@PathVariable String
	// id) {
	// return ResponseEntity.ok(new ApiResponse<>("success", "Staff retrieved
	// successfully", staffService.getStaff(id)));
	// }

	// @PostMapping
	// public ResponseEntity<ApiResponse<StaffDto>> createStaff(@RequestBody
	// StaffDto staffDto) {
	// StaffDto createdStaff = staffService.createStaff(staffDto);

	// return ResponseEntity.status(HttpStatus.CREATED).body(new
	// ApiResponse<StaffDto>("success", "Staff created successfully",
	// createdStaff));
	// }

	// @PatchMapping
	// public ResponseEntity<ApiResponse<StaffDto>> updateStaff(@RequestBody
	// StaffDto staffDto) {
	// StaffDto updatedStaff = staffService.updateStaff(staffDto);

	// return ResponseEntity.status(HttpStatus.OK).body(new
	// ApiResponse<StaffDto>("success", "Staff updated successfully",
	// updatedStaff));
	// }

	// @DeleteMapping("/{id}")
	// public ResponseEntity<ApiResponse<StaffDto>> deleteStaff(@PathVariable String
	// id) {
	// StaffDto deletedStaff = staffService.deleteStaff(id);

	// return ResponseEntity.status(HttpStatus.OK).body(new
	// ApiResponse<StaffDto>("success", "Staff deleted successfully",
	// deletedStaff));
	// }
}
