package com.studentmanagement.lop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.lop.model.LopDto;
import com.studentmanagement.lop.repository.LopRepository;
import com.studentmanagement.lop.service.LopService;

@Service
public class LopServiceImpl implements LopService {
    @Autowired
    private LopRepository lopRepository;
    
    @Override
    public List<LopDto> getLopWithStaff() {
        List<LopDto> lops = lopRepository.getLopWithStaff();

        return lops;
    }
}
