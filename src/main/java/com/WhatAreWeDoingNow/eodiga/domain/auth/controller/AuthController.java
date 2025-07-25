package com.WhatAreWeDoingNow.eodiga.domain.auth.controller;

import com.WhatAreWeDoingNow.eodiga.domain.auth.dto.LoginDto;
import com.WhatAreWeDoingNow.eodiga.domain.auth.dto.RegisterDto;
import com.WhatAreWeDoingNow.eodiga.domain.auth.service.AuthService;
import com.WhatAreWeDoingNow.eodiga.domain.jwt.dto.JwtTokenDto;
import com.WhatAreWeDoingNow.eodiga.global.common.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPath.AUTH_ROOT)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody RegisterDto dto) {
        authService.signUp(dto);
        return ResponseEntity.ok("회원가입 성공");
    }

    // 로그인 - 이메일, 비밀번호 받고 토큰 반환
    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody LoginDto dto) {
        JwtTokenDto tokens = authService.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok(tokens);
    }

    // 리프레시 토큰으로 엑세스 토큰 재발급
    @PostMapping("/refresh")
    public ResponseEntity<JwtTokenDto> refreshToken(@RequestBody JwtTokenDto refreshRequest) {
        JwtTokenDto tokens = authService.refreshAccessToken(refreshRequest.refreshToken());
        return ResponseEntity.ok(tokens);
    }
}
