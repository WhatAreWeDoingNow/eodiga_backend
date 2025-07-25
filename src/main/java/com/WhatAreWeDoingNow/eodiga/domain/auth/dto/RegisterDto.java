package com.WhatAreWeDoingNow.eodiga.domain.auth.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String email;
    private String password;
    private String username;
}