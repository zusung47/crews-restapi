package com.mentaljava.mentaljavarestapiproject.table.notice.service;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.table.admin.dto.AdminDTO;
import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.notice.dto.NoticeDTO;
import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import com.mentaljava.mentaljavarestapiproject.table.notice.repository.NoticeRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeService {

    private final NoticeRepository noticeRepository;
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
    public String deleteNotice(Integer noticeId) {
        int result = 0;

        try {
            Notice notice = noticeRepository.findByNoticeId(noticeId);
            if(notice != null){
                noticeRepository.delete(notice);
                result = 1;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (result > 0) ? "공지사항 삭제 성공" : "공지사항 삭제 실패";
    }

    @Transactional
    public String insertNotice(NoticeDTO noticeDTO) {
        int result = 0;
        try {
            Admin admin = noticeRepository.findByAdminId("admin1");

            AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
            log.info("[noticeService] insert adminDTO ===========> " + adminDTO);
            noticeDTO.setAdminId(adminDTO);
            noticeDTO.setNoticeDate(LocalDate.now());
            noticeDTO.setDeleteStatus(0);

            Notice notice = modelMapper.map(noticeDTO, Notice.class);
            noticeRepository.save(notice);
            result = 1;

        } catch (Exception e) {
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

    public int selectTotalNotice() {

        List<Notice> noticeList = noticeRepository.findByDeleteStatus(0);

        return noticeList.size();
    }

    public List<NoticeDTO> selectNoticeListWithPaging(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("noticeId").descending());

        Page<Notice> result = noticeRepository.findByDeleteStatus(0, paging);

        List<NoticeDTO> noticeDTOList = result.stream()
                .map(notice -> modelMapper.map(notice, NoticeDTO.class))
                .collect(Collectors.toList());

        return noticeDTOList;
    }
}
