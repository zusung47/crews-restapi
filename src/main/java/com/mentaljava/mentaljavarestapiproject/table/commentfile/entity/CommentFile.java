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
    private int commentId;

    @Column(name = "UPLOAD_DATE")
    private Date uploadDate;

    @Column(name = "ORIGIN_NAME")
    private String originName;

    @Column(name = "RENAME_NAME")
    private String renameName;

    @Column(name = "DELETE_STATUS")
    private int deleteStatus;

    @ManyToOne
    @JoinColumn(name = "COMMENT_ID")
    private CertificationComment commnetId;
}
