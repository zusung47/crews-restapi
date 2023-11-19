package com.mentaljava.mentaljavarestapiproject.table.admin.repository;

import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findById(String adminId);
    Admin findByAdminId(String adminId);
}
