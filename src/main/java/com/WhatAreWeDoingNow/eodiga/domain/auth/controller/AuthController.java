package com.WhatAreWeDoingNow.eodiga.domain.auth.controller;

import com.WhatAreWeDoingNow.eodiga.domain.auth.dto.EmailCheckDto;
import com.WhatAreWeDoingNow.eodiga.domain.auth.dto.LoginDto;
import com.WhatAreWeDoingNow.eodiga.domain.auth.dto.RegisterDto;
import com.WhatAreWeDoingNow.eodiga.domain.auth.service.AuthService;
import com.WhatAreWeDoingNow.eodiga.domain.jwt.dto.JwtTokenDto;
import com.WhatAreWeDoingNow.eodiga.domain.jwt.dto.RefreshTokenDto;
import com.WhatAreWeDoingNow.eodiga.global.common.ApiPath;
import com.WhatAreWeDoingNow.eodiga.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPath.AUTH_ROOT)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/email/check")
    public ResponseEntity<Response> checkEmail(@RequestBody EmailCheckDto emailCheckDto) {
        authService.checkEmail(emailCheckDto);
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), null, "사용 가능한 이메일 입니다."));
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<Response<String>> signUp(@RequestBody RegisterDto dto) {
        authService.signUp(dto);
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), null, "회원가입이 완료 되었습니다."));
    }

    // 로그인 - 이메일, 비밀번호 받고 토큰 반환
    @PostMapping("/login")
    public ResponseEntity<Response<JwtTokenDto>> login(@RequestBody LoginDto dto) {
        JwtTokenDto tokens = authService.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), tokens, "로그인이 완료 되었습니다."));
    }

    // 리프레시 토큰으로 엑세스 토큰 재발급
    @PostMapping("/refresh")
    public ResponseEntity<Response<RefreshTokenDto>> refreshToken(@RequestHeader("Authorization") String refreshToekn) {
        refreshToekn = refreshToekn.replace("Bearer ", "");
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), authService.refreshAccessToken(refreshToekn), "Access token이 재발급 되었습니다."));
    }
}
