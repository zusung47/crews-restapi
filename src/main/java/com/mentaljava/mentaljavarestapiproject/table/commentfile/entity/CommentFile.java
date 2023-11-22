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
    @Column(name = "COMMENT_FILE_ID")
    private Integer commentFileId;

    @Column(name = "UPLOAD_DATE")
    private Date uploadDate;

    @Column(name = "ORIGIN_NAME")
    private String originName;

    @Column(name = "RENAME_NAME")
    private String renameName;

    @Column(name = "DELETE_STATUS")
    private Integer deleteStatus;

    @ManyToOne
    @JoinColumn(name = "COMMENT_ID")
    private CertificationComment commentId;

    public CommentFile() {}

    public void setCommentFileId(Integer commentFileId) {
        this.commentFileId = commentFileId;
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

}
