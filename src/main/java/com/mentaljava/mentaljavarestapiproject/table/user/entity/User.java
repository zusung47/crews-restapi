package com.mentaljava.mentaljavarestapiproject.table.user.entity;

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
    private int diamondCount;

    @Column(name = "JOIN_DATE")
    private Date joinDate;

    @Column(name = "OUT_DATE")
    private Date outDate;

    @Column(name = "PERMISSION_TYPE")
    private String permissionType;
}
