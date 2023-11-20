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
    @GeneratedValue
    private Integer attachmentId;

    @Column(name = "ORIGIN_FILE_NAME")
    private String originFileName;

    @Column(name = "NEW_FILE_NAME")
    private String newFileName;

    @Column(name = "UPLOAD_DATE")
    private Date uploadDate;

    @ManyToOne
    @JoinColumn(name = "NOTICE_ID")
    private Notice noticeId;

    public NoticeFile() {}

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public void setNoticeId(Notice noticeId) {
        this.noticeId = noticeId;
    }
}
