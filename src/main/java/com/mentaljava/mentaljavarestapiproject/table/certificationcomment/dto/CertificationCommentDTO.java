package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.dto;

import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CertificationCommentDTO {

    private Integer commentId;

    private String commentContent;

    private CertificationPost postId;

    private UserDTO userId;

    private Date writeDate;

    private Integer deleteStatus;
}
