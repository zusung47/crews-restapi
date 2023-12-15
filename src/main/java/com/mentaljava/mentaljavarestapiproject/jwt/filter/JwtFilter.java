package com.mentaljava.mentaljavarestapiproject.jwt.filter;

import com.mentaljava.mentaljavarestapiproject.common.AuthConstants;
import com.mentaljava.mentaljavarestapiproject.jwt.handler.TokenProvider;
import com.mentaljava.mentaljavarestapiproject.util.TokenUtils;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/* OncePerRequestFilter : 사용자의 요청에 한번 동작하는 필터 - 동일한 요청 안에서 한번만 Filtering할 수 있게 해주는 것
 * 인증, 인가를 거치고 특정 URL로 포워딩하면 요청이 들어왔으니 인증, 인가 Filter 다시 실행시켜야하지만
 * OncePerRequestFilter를 이용하면 인증, 인가를 한번만 거치고 다음 Logic을 수행할 수 있게 해준다.
 * */
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = resolveToken(request);  // accessToken

        /* 추출한 토큰의 유효성 검사 후 인증을 위해 Authentication 객체를 SecurityContextHolder에 담는다.*/
        if(StringUtils.hasText(jwt) && TokenUtils.isValidToken(jwt)){
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response); // 다음 filterchain 진행
    }


    /* Request Header에서 토큰 정보 꺼내기(여기서 위에서 선언한 두개의 static변수를 사용할꺼)*/
    private String resolveToken(HttpServletRequest request){

        String bearerToken = request.getHeader(AuthConstants.AUTH_HEADER.toLowerCase());
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(AuthConstants.TOKEN_TYPE)){
            return TokenUtils.splitHeader(bearerToken); // 사용자가 보낸 토큰 값 추출
        }
        return null;
    }
}
