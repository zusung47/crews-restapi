package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.entity;

import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;

import java.time.LocalDate;
import java.util.Optional;
import javax.persistence.*;

import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

@Entity
@Table(name = "CERTIFICATION_COMMENT")
@AllArgsConstructor
@Getter
@ToString
public class CertificationComment {

    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue
    private Integer commentId;

    @Column(name = "COMMENT_CONTENT")
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private CertificationPost postId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User userId;

    @Column(name = "WRITE_DATE")
    private LocalDate writeDate;

    @Column(name = "DELETE_STATUS")
    private Integer deleteStatus;

    public CertificationComment() {}

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setPostId(CertificationPost postId) {
        this.postId = postId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setWriteDate(LocalDate writeDate) {
        this.writeDate = writeDate;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
