package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.dto;

import com.mentaljava.mentaljavarestapiproject.table.certificationpost.dto.CertificationPostDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import java.time.LocalDate;
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

    private CertificationPostDTO postId;

    private UserDTO userId;

    private LocalDate writeDate;

    private Integer deleteStatus;

    private String commentImageUrl;
}
