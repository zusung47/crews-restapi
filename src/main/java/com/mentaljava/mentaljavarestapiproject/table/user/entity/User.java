package com.mentaljava.mentaljavarestapiproject.table.user.entity;

import java.time.LocalDate;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER")
@AllArgsConstructor
@Getter
@ToString
public class User {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "DIAMOND_COUNT")
    private Integer diamondCount;

    @Column(name = "JOIN_DATE")
    private LocalDate joinDate;

    @Column(name = "OUT_DATE")
    private LocalDate outDate;

    @Column(name = "PERMISSION_TYPE")
    private String permissionType;

    @Column(name = "REPORT_STATUS")
    private String reportStatus;

    public User() {}

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setDiamondCount(Integer diamondCount) {
        this.diamondCount = diamondCount;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public void setOutDate(LocalDate outDate) {
        this.outDate = outDate;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public void setReportStatus(String reportStatus) { this.reportStatus = reportStatus; }

    @Builder
    public User(String email,String nickname, LocalDate joinDate, Integer daimondCount,String permissionType, String reportStatus) {
        this.userId = email;
        this.nickname = nickname;
        this.joinDate = joinDate;
        this.diamondCount = daimondCount;
        this.permissionType = permissionType;
        this.reportStatus = reportStatus;
    }


}
