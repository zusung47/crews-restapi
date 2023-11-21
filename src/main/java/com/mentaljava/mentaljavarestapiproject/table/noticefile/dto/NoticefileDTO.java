package com.mentaljava.mentaljavarestapiproject.table.noticefile.dto;

import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NoticefileDTO {

    private Integer attachmentId;

    private String originFileName;

    private String newFileName;

    private Date uploadDate;

    private Notice noticeId;
}
