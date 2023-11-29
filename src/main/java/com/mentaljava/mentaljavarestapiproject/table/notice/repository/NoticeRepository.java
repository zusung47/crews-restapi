package com.mentaljava.mentaljavarestapiproject.table.notice.repository;

import com.mentaljava.mentaljavarestapiproject.table.admin.dto.AdminDTO;
import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    Notice findByNoticeId(Integer noticeId);

    @Query("SELECT a FROM Admin a where a.adminId=?1")
    Admin findByAdminId(String admin1);

    List<Notice> findByDeleteStatus(int i);

    Page<Notice> findByDeleteStatus(int i, Pageable paging);
}
