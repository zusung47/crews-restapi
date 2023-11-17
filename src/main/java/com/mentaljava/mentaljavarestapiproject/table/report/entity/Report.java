package com.mentaljava.mentaljavarestapiproject.table.report.entity;

import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "REPORT")
@AllArgsConstructor
@Getter
@ToString
public class Report {

    @Id
    @Column(name = "REPORT_ID")
    private int reportId;

    @Column(name = "REPORT_TARGET")
    private String reportTarget;

    @Column(name = "REPORT_REASON")
    private String reportReason;

    @Column(name = "REPORT_CONTENT")
    private String reportContent;

    @ManyToOne
    @JoinColumn(name = "REPORTER")
    private User reporter;

    @Column(name = "REPORT_CATEGORY")
    private int reportCategory;

    @Column(name = "REPORT_STATUS")
    private int reportStatus;
}
