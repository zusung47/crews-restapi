package com.mentaljava.mentaljavarestapiproject.table.commentfile.dto;

import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.entity.CertificationComment;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class commentfileDTO {

    private int commentId;

    private Date uploadDate;

    private String originName;

    private String renameName;

    private int deleteStatus;

    private CertificationComment commnetId;
}

