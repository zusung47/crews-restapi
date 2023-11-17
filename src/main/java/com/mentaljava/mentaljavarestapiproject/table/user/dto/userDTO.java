package com.mentaljava.mentaljavarestapiproject.table.user.dto;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class userDTO {

    private String userId;

    private String nickname;

    private int diamondCount;

    private Date joinDate;

    private Date outDate;

    private String permissionType;
}

