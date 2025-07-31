package com.WhatAreWeDoingNow.eodiga.domain.auth.service;

import com.WhatAreWeDoingNow.eodiga.domain.auth.dto.RegisterDto;
import com.WhatAreWeDoingNow.eodiga.domain.jwt.dto.JwtTokenDto;
import com.WhatAreWeDoingNow.eodiga.domain.jwt.dto.RefreshTokenDto;
import com.WhatAreWeDoingNow.eodiga.domain.redis.service.JwtRedisService;
import com.WhatAreWeDoingNow.eodiga.domain.user.entity.User;
import com.WhatAreWeDoingNow.eodiga.domain.user.repository.UserRepository;
import com.WhatAreWeDoingNow.eodiga.global.utility.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtRedisService jwtRedisService;
    private final JwtProvider jwtProvider;

    public void signUp(RegisterDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .username(dto.getUsername())
                .role(dto.getRole())
                .build();

        userRepository.save(user);
    }


    // 로그인 - 이메일과 비밀번호 체크 후 토큰 발급
    public JwtTokenDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtProvider.generateAccessToken(email);
        String refreshToken = jwtProvider.generateRefreshToken(email);

        // Redis에 Refresh Token 저장 (email 키로)
        jwtRedisService.save(email, refreshToken);

        return new JwtTokenDto(accessToken, refreshToken);
    }

    // 클라이언트가 리프레시 토큰을 보내면 Access Token 재발급
    public RefreshTokenDto refreshAccessToken(String refreshToken) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new RuntimeException("유효하지 않은 리프레시 토큰입니다.");
        }

        String email = jwtProvider.extractEmail(refreshToken);

        String savedToken = jwtRedisService.get(email);
        if (savedToken == null || !savedToken.equals(refreshToken)) {
            throw new RuntimeException("저장된 리프레시 토큰과 일치하지 않습니다.");
        }

        String newAccessToken = jwtProvider.generateAccessToken(email);
        String newRefreshToken = jwtProvider.generateRefreshToken(email);

        jwtRedisService.save(email, newRefreshToken);

        return new RefreshTokenDto(newAccessToken);
    }

}