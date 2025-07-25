package com.WhatAreWeDoingNow.eodiga.domain.auth.service;

import com.WhatAreWeDoingNow.eodiga.domain.auth.dto.RegisterDto;
import com.WhatAreWeDoingNow.eodiga.domain.jwt.dto.JwtTokenDto;
import com.WhatAreWeDoingNow.eodiga.domain.user.entity.User;
import com.WhatAreWeDoingNow.eodiga.domain.user.repository.UserRepository;
import com.WhatAreWeDoingNow.eodiga.global.utility.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public void signUp(RegisterDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .username(dto.getUsername())
                .build();

        userRepository.save(user);
    }


    // 로그인 - 이메일과 비밀번호 체크 후 토큰 발급
    public JwtTokenDto login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("존재하지 않는 사용자입니다.");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtProvider.generateAccessToken(email);
        String refreshToken = jwtProvider.generateRefreshToken(email);

        return new JwtTokenDto(accessToken, refreshToken);
    }

    // 리프레시 토큰으로 Access 토큰 재발급
    public JwtTokenDto refreshAccessToken(String refreshToken) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new RuntimeException("유효하지 않은 리프레시 토큰입니다.");
        }

        String email = jwtProvider.extractEmail(refreshToken);

        // 필요시 DB에 저장된 리프레시 토큰과 비교해서 검증 (여기선 생략 가능)

        String newAccessToken = jwtProvider.generateAccessToken(email);
        String newRefreshToken = jwtProvider.generateRefreshToken(email); // 선택적, 리프레시도 갱신 가능

        return new JwtTokenDto(newAccessToken, newRefreshToken);
    }
}