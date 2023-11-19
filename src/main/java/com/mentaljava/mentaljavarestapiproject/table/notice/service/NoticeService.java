package com.mentaljava.mentaljavarestapiproject.table.notice.service;

import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.admin.repository.AdminRepository;
import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import com.mentaljava.mentaljavarestapiproject.table.notice.repository.NoticeRepository;
import com.mentaljava.mentaljavarestapiproject.table.noticefile.repository.NoticeFileRepository;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;
    private final AdminRepository adminRepository;

    public List<Notice> findNotice() {
        return noticeRepository.findAll();
    }

    @Transactional
    public Notice findOne(Integer Id) {
        return noticeRepository.findById(Id).orElse(null);
    }

    @Transactional
    public void updateNotice(Integer noticeId, String noticeTitle, String noticeContent) {
        Notice notice = noticeRepository.findByNoticeId(noticeId);
        notice.setNoticeContent(noticeContent);
        notice.setNoticeTitle(noticeTitle);
        noticeRepository.save(notice);
    }

    @Transactional
    public void deleteNotice(Integer noticeId) {
        noticeFileRepository.deleteById(noticeId);
        noticeRepository.deleteById(noticeId);
    }

    @Transactional
    public void registNotice(String adminId, String noticeTitle, String noticeContent) {

        Admin admin = adminRepository.findByAdminId(adminId);

        Notice notice = new Notice();
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeContent(noticeContent);
        notice.setAdminId(admin.getAdminId());
        notice.setNoticeDate(new Date(System.currentTimeMillis()));
        noticeRepository.save(notice);
    }
}
