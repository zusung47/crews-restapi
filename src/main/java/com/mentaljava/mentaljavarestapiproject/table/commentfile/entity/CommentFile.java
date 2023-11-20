package com.mentaljava.mentaljavarestapiproject.table.commentfile.entity;

import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.entity.CertificationComment;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENT_FILE")
@AllArgsConstructor
@Getter
@ToString
public class CommentFile {

    @Id
    @Column(name = "COMMENT_ID")
    private Integer commentId;

    @Column(name = "UPLOAD_DATE")
    private Date uploadDate;

    @Column(name = "ORIGIN_NAME")
    private String originName;

    @Column(name = "RENAME_NAME")
    private String renameName;

    @Column(name = "DELETE_STATUS")
    private Integer deleteStatus;

//    @ManyToOne
//    @JoinColumn(name = "COMMENT_ID")
//    private CertificationComment commnetId;

    public CommentFile() {}

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public void setRenameName(String renameName) {
        this.renameName = renameName;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

//    public void setCommnetId(CertificationComment commnetId) {
//        this.commnetId = commnetId;
//    }
}
