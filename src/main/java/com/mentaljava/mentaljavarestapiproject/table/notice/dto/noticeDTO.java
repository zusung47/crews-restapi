package com.mentaljava.mentaljavarestapiproject.table.notice.dto;

import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
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
public class noticeDTO {

    private Integer noticeId;

    private String noticeTitle;

    private String noticeContent;

    private Date noticeDate;

    private int deleteStatus;

    private Admin adminId;
}
