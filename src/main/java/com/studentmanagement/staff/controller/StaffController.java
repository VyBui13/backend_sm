package com.studentmanagement.staff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.common.response.ApiResponse;
import com.studentmanagement.staff.dto.StaffDto;
import com.studentmanagement.staff.service.StaffService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/staffs")
public class StaffController {
    
    @Autowired
    private StaffService staffService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StaffDto>>> getAllStaffs() {
        return ResponseEntity.ok(new ApiResponse<>("success", "All staffs retrieved successfully", staffService.getAllStaffs()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StaffDto>> getStaff(@PathVariable String id) {
        return ResponseEntity.ok(new ApiResponse<>("success", "Staff retrieved successfully", staffService.getStaff(id)));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<StaffDto>> createStaff(@RequestBody StaffDto staffDto) {
        StaffDto createdStaff = staffService.createStaff(staffDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<StaffDto>("success", "Staff created successfully", createdStaff));
    }
    
    @PatchMapping
    public ResponseEntity<ApiResponse<StaffDto>> updateStaff(@RequestBody StaffDto staffDto) {
        StaffDto updatedStaff = staffService.updateStaff(staffDto);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<StaffDto>("success", "Staff updated successfully", updatedStaff));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<StaffDto>> deleteStaff(@PathVariable String id) {
        StaffDto deletedStaff = staffService.deleteStaff(id);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<StaffDto>("success", "Staff deleted successfully", deletedStaff));
    }
}
