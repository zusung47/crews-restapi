package com.mentaljava.mentaljavarestapiproject.table.notice.entity;

import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NOTICE")
@AllArgsConstructor
@Getter
@ToString
public class Notice {

    @Id
    @Column(name = "NOTICE_ID") @GeneratedValue
    private Integer noticeId;

    @Column(name = "NOTICE_TITLE")
    private String noticeTitle;

    @Column(name = "NOTICE_CONTENT")
    private String noticeContent;

    @Column(name = "NOTICE_DATE")
    private Date noticeDate;

    @Column(name = "DELETE_STATUS")
    private int deleteStatus;

    @ManyToOne
    @JoinColumn(name = "ADMIN_ID")
    private Admin adminId;

    public Notice() {}

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public void setAdminId(Admin adminId) {
        this.adminId = adminId;
    }
}
