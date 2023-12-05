package com.mentaljava.mentaljavarestapiproject.table.login.controller;

import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.login.service.KakaoService;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import com.mentaljava.mentaljavarestapiproject.table.login.dto.KakaoTokenResponse;
import com.mentaljava.mentaljavarestapiproject.table.login.util.KakaoTokenJsonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class KakaoController {

    @Value("${kakao.client_id}")
    private String client_id;

    @Value("${kakao.redirect_uri}")
    private String redirect_uri;

    private final KakaoService kakaoService;
    private final KakaoTokenJsonData kakaoTokenJsonData;



    @GetMapping("/oauth")
    public ResponseEntity<ResponseDTO> kakaoLogin(HttpServletRequest request) throws IOException {

        String code = request.getParameter("code");
        KakaoTokenResponse accessToken = kakaoTokenJsonData.getToken(code);
        log.info("[token] ===== "+accessToken);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(accessToken.getAccess_token());
        log.info("id : " + userInfo.get("id"));
        // User 로그인, 또는 회원가입 로직 추가
        UserDTO userDTO = kakaoService.createUser(userInfo);
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "회원 가입 성공", userDTO));

    }


}
