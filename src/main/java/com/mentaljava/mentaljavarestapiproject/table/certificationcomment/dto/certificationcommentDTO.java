package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.dto;

import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class certificationcommentDTO {

    private Integer commentId;

    private String commentContent;

    private CertificationPost postId;

    private User userId;

    private Date writeDate;

    private Integer deleteStatus;
}
