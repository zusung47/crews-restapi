package com.mentaljava.mentaljavarestapiproject.table.notice.service;

import com.mentaljava.mentaljavarestapiproject.table.admin.dto.AdminDTO;
import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.admin.repository.AdminRepository;
import com.mentaljava.mentaljavarestapiproject.table.notice.dto.NoticeDTO;
import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import com.mentaljava.mentaljavarestapiproject.table.notice.repository.NoticeRepository;
import com.mentaljava.mentaljavarestapiproject.table.noticefile.repository.NoticeFileRepository;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.Local;
import org.aspectj.weaver.ast.Not;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
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
    public void deleteNotice(Integer noticeId) {
        noticeFileRepository.deleteById(noticeId);
        noticeRepository.deleteById(noticeId);
    }


    @Transactional
    public String insertNotice(NoticeDTO noticeDTO) {
        int result =0;
        try {
            Admin admin = noticeRepository.findByAdminId("admin1");

            AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
            log.info("[noticeService] insert adminDTO ===========> " + adminDTO);
            noticeDTO.setAdminId(adminDTO);
            noticeDTO.setNoticeDate(LocalDate.now());
            noticeDTO.setDeleteStatus(0);

            Notice notice = modelMapper.map(noticeDTO, Notice.class);
            noticeRepository.save(notice);
            result =1;

        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return (result > 0) ? "공지사항 등록 성공" : "공지사항 등록 실패";
    }

    @Transactional
    public String updateNotice(NoticeDTO noticeDTO) {

        int result = 0;

        try {
            Notice notice = noticeRepository.findByNoticeId(noticeDTO.getNoticeId());

            notice.setNoticeContent(noticeDTO.getNoticeContent());
            notice.setNoticeTitle(noticeDTO.getNoticeTitle());
            noticeRepository.save(notice);
            result = 1;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (result > 0) ? "공지사항 업데이트 성공" : "공지사항 업데이트 실패";
    }
}
