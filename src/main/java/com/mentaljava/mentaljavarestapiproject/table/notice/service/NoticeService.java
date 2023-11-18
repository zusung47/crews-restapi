package com.mentaljava.mentaljavarestapiproject.table.notice.service;

import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import com.mentaljava.mentaljavarestapiproject.table.notice.repository.NoticeRepository;
import com.mentaljava.mentaljavarestapiproject.table.noticefile.repository.NoticeFileRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;

    @Transactional
    public void saveNotice(Notice notice) {
        noticeRepository.save(notice);
    }

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
}
