package com.mentaljava.mentaljavarestapiproject.table.admin.service;

import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.admin.repository.AdminRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;


    public List<Admin> findAdmin() {

        return adminRepository.findAll();
    }

    public Admin findOne(Admin adminId) {
        return adminRepository.findById(adminId.getAdminId()).orElse(null);
    }
}
