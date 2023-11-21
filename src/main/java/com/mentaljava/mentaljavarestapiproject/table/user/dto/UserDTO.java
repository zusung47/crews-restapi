package com.mentaljava.mentaljavarestapiproject.table.user.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private String userId;

    private String nickname;

    private Integer diamondCount;

    private Date joinDate;

    private Date outDate;

    private String permissionType;
}

