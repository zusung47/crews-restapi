package com.mentaljava.mentaljavarestapiproject.table.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TokenDTO {

    private String grantType;       // 토큰 타입
    private String memberName;      // 인증받은 회원 이름
    private String accessToken;     // 엑세스 토큰
}
