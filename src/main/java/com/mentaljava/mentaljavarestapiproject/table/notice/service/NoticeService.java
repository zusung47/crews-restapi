package com.mentaljava.mentaljavarestapiproject.table.notice.service;

import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.admin.repository.AdminRepository;
import com.mentaljava.mentaljavarestapiproject.table.notice.dto.NoticeDTO;
import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import com.mentaljava.mentaljavarestapiproject.table.notice.repository.NoticeRepository;
import com.mentaljava.mentaljavarestapiproject.table.noticefile.repository.NoticeFileRepository;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    public List<NoticeDTO> findNotice() {

        List<Notice> noticeList = noticeRepository.findAll();
        List<NoticeDTO> noticeDTOList = noticeList.stream().map(notice ->
                modelMapper.map(notice, NoticeDTO.class)).collect(Collectors.toList());
        return noticeDTOList;
    }


    public NoticeDTO findOne(Integer Id) {
        Notice notice = noticeRepository.findByNoticeId(Id);
        NoticeDTO noticeDTO = modelMapper.map(notice, NoticeDTO.class);
        return noticeDTO;
    }

    @Transactional
    public String updateNotice(Integer noticeId, String noticeTitle, String noticeContent) {
        int result = 0;
        Notice notice = noticeRepository.findByNoticeId(noticeId);
        notice.setNoticeContent(noticeContent);
        notice.setNoticeTitle(noticeTitle);
        noticeRepository.save(notice);

        if(notice != null){
            result =1;
        }

        return (result > 0) ? "공지사항 수정 성공" : "공지사항 수정 실패";
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

    @Transactional
    public void update(Integer noticeId, String noticeTitle, String noticeContent) {
        Notice notice = noticeRepository.findByNoticeId(noticeId);
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeContent(noticeContent);
    }

    public Notice updateOneNotice(Integer noticeId) {
        return noticeRepository.findByNoticeId(noticeId);
    }
}
