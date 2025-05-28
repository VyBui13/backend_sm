package com.studentmanagement.staff.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.studentmanagement.key.repository.KeyRepository;
import com.studentmanagement.staff.dto.CreateStaffDto;
import com.studentmanagement.staff.dto.StaffDto;
import com.studentmanagement.staff.mapper.StaffMapper;
import com.studentmanagement.staff.model.Staff;
import com.studentmanagement.staff.repository.StaffRepository;
import com.studentmanagement.staff.service.StaffService;
import com.studentmanagement.util.CryptoUtil;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final CryptoUtil cryptoUtil;
    private final KeyRepository keyRepository;

    public StaffServiceImpl(
            StaffRepository staffRepository,
            CryptoUtil cryptoUtil,
            KeyRepository keyRepository) {
        this.staffRepository = staffRepository;
        this.cryptoUtil = cryptoUtil;
        this.keyRepository = keyRepository;
    }

    @Override
    public Boolean signIn(String username, String password) {
        byte[] hashedPassword = cryptoUtil.hash(password);

        Boolean isAuthorized = staffRepository.signIn(username, hashedPassword);

        return isAuthorized;
    }

    @Override
    public StaffDto getStaffInfoByCredentials(String username, String password) {
        byte[] hashedPassword = cryptoUtil.hash(password);

        Staff foundStaff = staffRepository.getStaffInfoByCredentials(username, hashedPassword);

        if (foundStaff == null) {
            return null;
        }

        byte[] decryptedSalaryBytes = cryptoUtil.decrypt(
                foundStaff.getSalary(),
                keyRepository.getPrivateKey(foundStaff.getId()));

        Long decryptedSalary = Long.parseLong(new String(decryptedSalaryBytes, StandardCharsets.UTF_16LE));

        return new StaffDto(foundStaff.getId(), foundStaff.getFullname(), decryptedSalary,
                foundStaff.getEmail());
    }

    @Override
    public StaffDto createStaff(CreateStaffDto wedontusethisvarindev_____createStaffDto) {
        // this is just a place to mock data

        CreateStaffDto staffs[] = {
                new CreateStaffDto(
                        "NV001",
                        "Nguyễn Văn An",
                        "an@school.com",
                        5000000L,
                        "nv_an",
                        "password123",
                        "NV001"),
                new CreateStaffDto(
                        "NV002",
                        "Trần Thị Bình",
                        "binh@school.com",
                        5500000L,
                        "nv_binh",
                        "password456",
                        "NV002")
        };

        for (CreateStaffDto staff : staffs) {
            // generate a key pair
            KeyPair keyPair = cryptoUtil.generateKeyPair();
            // save it to the sqlite database
            keyRepository.insertKeyPair(staff.getId(), keyPair.getPublic(), keyPair.getPrivate());

            // encrypt salary with the public key and the algorithm specified in CryptoUtil
            byte[] encryptedSalary = cryptoUtil.encrypt(Long.toString(staff.getSalary()), keyPair.getPublic());

            // hash the password with the algorithm specified in CryptoUtil
            byte[] hashedPassword = cryptoUtil.hash(staff.getPassword());

            staffRepository.createStaff(
                    staff.getId(),
                    staff.getFullname(),
                    staff.getEmail(),
                    encryptedSalary,
                    staff.getUsername(),
                    hashedPassword,
                    keyPair.getPublic().toString());
        }

        return new StaffDto();
    }

    @Override
    public List<StaffDto> getAllStaffs() {
        return new ArrayList<StaffDto>();
    }

    @Override
    public StaffDto getStaff(String id) {
        return new StaffDto();
    }

    @Override
    public StaffDto updateStaff(StaffDto staffDto) {
        return new StaffDto();
    }

    @Override
    public StaffDto deleteStaff(String id) {
        return new StaffDto();
    }

    @Override
    public Boolean DEPRECATEDsignIn(String username, String password) {
        Boolean isAuthorized = staffRepository.DEPRECATEDsignIn(username, password);

        return isAuthorized;
    }

    @Override
    public StaffDto DEPRECATEDgetStaffInfoByCredentials(String username, String password) {
        Staff foundStaff = staffRepository.DEPRECATEDgetStaffInfoByCredentials(username, password);

        if (foundStaff == null) {
            return null;
        }

        return StaffMapper.toDto(foundStaff);
    }
}
