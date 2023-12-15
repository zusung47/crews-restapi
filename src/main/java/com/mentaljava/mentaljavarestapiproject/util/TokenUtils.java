package com.mentaljava.mentaljavarestapiproject.util;

import com.mentaljava.mentaljavarestapiproject.exception.TokenException;
import com.mentaljava.mentaljavarestapiproject.table.admin.dto.AdminDTO;
import io.jsonwebtoken.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TokenUtils {

    private static String jwtSecretKey;
    private static Long tokenValidateTime;

    @Value("${jwt.key}")
    public void setJwtSecretKey(String jwtSecretKey) {
        TokenUtils.jwtSecretKey = jwtSecretKey;
    }

    @Value("${jwt.time}")
    public void setTokenValidateTime(Long tokenValidateTime) {
        TokenUtils.tokenValidateTime = tokenValidateTime;
    }

    public static String splitHeader(String header) {
        if(!header.equals("")) {
            return header.split(" ")[1];

        } else {
            return null;
        }
    }

    public static boolean isValidToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
                    .parseClaimsJws(token);
            return true;
        } catch(SecurityException | MalformedJwtException e) {
            log.info("[TokenProvider] 잘못된 JWT 서명입니다.");
            throw new TokenException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e){
            log.info("[TokenProvider] 만료된 JWT 토큰입니다.");
            throw new TokenException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e){
            log.info("[TokenProvider] 지원되지 않는 JWT 토큰입니다.");
            throw new TokenException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e){
            log.info("[TokenProvider] JWT 토큰이 잘못되었습니다.");
            throw new TokenException("JWT 토큰이 잘못되었습니다.");
        }
    }

    public static Claims getClaimsFromToken(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e){
            return e.getClaims(); // 토큰이 만료되어 예외가 발생하더라도 클레임 값들을 뽑을 수 있다.
        }
    }

    public static String generateJwtToken(AdminDTO user) {
        Date expireTime = new Date(System.currentTimeMillis() + tokenValidateTime);

        JwtBuilder builder = Jwts
                .builder()
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setSubject(user.getAdminId())
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS256, createSignature());

        return builder.compact();
    }

    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("type", "jwt");
        header.put("alg", "HS256");
        header.put("date", System.currentTimeMillis());

        return header;
    }

    private static Map<String, Object> createClaims(AdminDTO user) {
        Map<String, Object> claims = new HashMap<>();

        List<String> roles = new ArrayList<>(); // 멤버의 권한을 추출

       roles.add(user.getPermissionName());

        claims.put("memberName", user.getUsername());
        claims.put("auth", roles);

        return claims;
    }

    private static Key createSignature() {
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
        return new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());
    }
}
