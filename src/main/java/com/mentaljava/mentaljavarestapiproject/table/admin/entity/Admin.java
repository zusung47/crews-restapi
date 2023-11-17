package com.mentaljava.mentaljavarestapiproject.table.admin.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ADMIN")
@AllArgsConstructor
@Getter
@ToString
public class Admin {

    @Id
    @Column(name = "ADMIN_ID")
    private String adminId;

    @Column(name = "ADMIN_PW")
    private String adminPw;

    @Column(name = "PERMISSION_NAME")
    private String permissionName;

    public Admin() {}

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setAdminPw(String adminPw) {
        this.adminPw = adminPw;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
