package com.mentaljava.mentaljavarestapiproject.table.report.dto;

import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class reportDTO {

    private int reportId;

    private String reportTarget;

    private String reportReason;

    private String reportContent;

    private User reporter;

    private int reportCategory;

    private int reportStatus;
}
