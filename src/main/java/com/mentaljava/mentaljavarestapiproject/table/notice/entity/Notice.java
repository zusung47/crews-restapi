package com.mentaljava.mentaljavarestapiproject.table.notice.entity;

import static javax.persistence.FetchType.LAZY;

import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import java.time.LocalDate;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import net.bytebuddy.asm.Advice.Local;

@Entity
@Table(name = "NOTICE")
@AllArgsConstructor
@Getter
@ToString
public class Notice {

    @Id
    @Column(name = "NOTICE_ID")
    @GeneratedValue
    private Integer noticeId;

    @Column(name = "NOTICE_TITLE")
    private String noticeTitle;

    @Column(name = "NOTICE_CONTENT")
    private String noticeContent;

    @Column(name = "NOTICE_DATE")
    private LocalDate noticeDate;

    @Column(name = "DELETE_STATUS")
    private Integer deleteStatus;

    @ManyToOne(fetch = LAZY)
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

    public void setNoticeDate(LocalDate noticeDate) {
        this.noticeDate = noticeDate;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public void setAdminId(Admin adminId) {
        this.adminId = adminId;
    }


    public void setAdminId(String adminId) {
    }

    public Notice build() {
        return new Notice(noticeId,noticeTitle,noticeContent,noticeDate,deleteStatus,adminId);
    }
}
