package com.mentaljava.mentaljavarestapiproject.table.noticefile.dto;

import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
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
public class noticefileDTO {

    private int attachmentId;

    private String originFileName;

    private String newFileName;

    private Date uploadDate;

    private Notice noticeId;
}
