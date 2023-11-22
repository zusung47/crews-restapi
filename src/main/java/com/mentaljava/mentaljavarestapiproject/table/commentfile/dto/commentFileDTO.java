package com.mentaljava.mentaljavarestapiproject.table.commentfile.dto;

import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.dto.CertificationCommentDTO;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class commentFileDTO {

    private Integer commentFIleId;

    private Date uploadDate;

    private String originName;

    private String renameName;

    private Integer deleteStatus;

    private CertificationCommentDTO commentId;
}

