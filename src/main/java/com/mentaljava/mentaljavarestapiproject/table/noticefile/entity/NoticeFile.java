package com.mentaljava.mentaljavarestapiproject.table.noticefile.entity;

import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NOTICE_FILE")
@AllArgsConstructor
@Getter
@ToString
public class NoticeFile {

    @Id
    @Column(name = "ATTACHMENT_ID")
    private int attachmentId;

    @Column(name = "ORIGIN_FILE_NAME")
    private String originFileName;

    @Column(name = "NEW_FILE_NAME")
    private String newFileName;

    @Column(name = "UPLOAD_DATE")
    private Date uploadDate;

    @ManyToOne
    @JoinColumn(name = "NOTICE_ID")
    private Notice noticeId;
}
