package com.mentaljava.mentaljavarestapiproject.jwt.handler;

import com.mentaljava.mentaljavarestapiproject.common.AuthConstants;
import com.mentaljava.mentaljavarestapiproject.table.admin.dto.AdminDTO;
import com.mentaljava.mentaljavarestapiproject.table.admin.dto.TokenDTO;
import com.mentaljava.mentaljavarestapiproject.util.TokenUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/* 토큰 생성, 토큰 인증(Authentication 객체 반환), 토큰 유효성검사 */
@Component
@Slf4j
public class TokenProvider {

    private final UserDetailsService userDetailsService;
    private ModelMapper modelMapper;

    public TokenProvider(UserDetailsService userDetailsService, ModelMapper modelMapper ){
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
    }

    /* 1. 토큰 생성 메소드 */
    public TokenDTO generateTokenDTO(AdminDTO member){

        AdminDTO memberDTO = modelMapper.map(member, AdminDTO.class);

        return new TokenDTO(AuthConstants.TOKEN_TYPE,
                member.getUsername(),
                TokenUtils.generateJwtToken(memberDTO));
    }

    /* 2. 토큰의 등록된 클레임의 subject에서 해당 회원의 아이디를 추출 */
    public String getUserId(String token) {
        return TokenUtils.getClaimsFromToken(token).getSubject();
    }

    /* 3. AccessToken으로 인증 객체 추출 */
    public Authentication getAuthentication(String token){

        log.info("[TokenProvider] getAuthentication Start =============================== ");
        /* 토큰에서 claim들을 추출(토큰 복호화) */
        Claims claims = TokenUtils.getClaimsFromToken(token);

        if(claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        log.info("[TokenProvider] ===================== {}",  userDetails.getAuthorities());

        log.info("[TokenProvider] getAuthentication End =============================== ");
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
