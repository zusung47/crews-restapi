package com.mentaljava.mentaljavarestapiproject.table.admin.dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminDTO {
    private String adminId;
    private String adminPw;
    private String permissionName;
}
