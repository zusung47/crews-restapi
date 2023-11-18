package com.mentaljava.mentaljavarestapiproject.table.notice.repository;

import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

}
