package com.mentaljava.mentaljavarestapiproject.table.notice.dto;

import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import java.time.LocalDate;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NoticeDTO {

    private Integer noticeId;

    private String noticeTitle;

    private String noticeContent;

    private LocalDate noticeDate;

    private Integer deleteStatus;

    private Admin adminId;
}
