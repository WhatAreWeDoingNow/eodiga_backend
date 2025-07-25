package com.WhatAreWeDoingNow.eodiga.domain.auth.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}