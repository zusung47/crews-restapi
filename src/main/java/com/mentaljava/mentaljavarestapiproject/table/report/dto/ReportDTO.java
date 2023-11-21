package com.mentaljava.mentaljavarestapiproject.table.report.dto;

import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReportDTO {

    private Integer reportId;

    private String reportTarget;

    private String reportReason;

    private String reportContent;

    private User reporter;

    private Integer reportCategory;

    private Integer reportStatus;
}
