package com.mentaljava.mentaljavarestapiproject.table.notice.repository;

import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    Notice findByNoticeId(Integer noticeId);
}
