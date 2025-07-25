package com.WhatAreWeDoingNow.eodiga.domain.jwt.dto;

public record JwtTokenDto(
        String accessToken,
        String refreshToken
) {}
