package com.studentmanagement.lop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.common.response.ApiResponse;
import com.studentmanagement.lop.model.LopDto;
import com.studentmanagement.lop.service.LopService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/lops")
public class LopController {
    @Autowired
    private LopService lopService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<LopDto>>> getLopWithStaff() {
        List<LopDto> lops = lopService.getLopWithStaff();

        return ResponseEntity.ok(new ApiResponse<List<LopDto>>("success", "All lops with staff retrieved successfully", lops));
    }
}
