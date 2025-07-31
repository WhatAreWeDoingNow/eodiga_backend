package com.WhatAreWeDoingNow.eodiga.domain.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class JwtRedisService {

    private final StringRedisTemplate redisTemplate;
    private final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60;

    public void save(String email, String refreshToken) {
        redisTemplate.opsForValue().set("RT:" + email, refreshToken, Duration.ofSeconds(REFRESH_TOKEN_EXPIRATION));
    }

    public String get(String email) {
        return redisTemplate.opsForValue().get("RT:" + email);
    }

    public void delete(String email) {
        redisTemplate.delete("RT:" + email);
    }

}
