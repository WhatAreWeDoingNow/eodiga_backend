package com.WhatAreWeDoingNow.eodiga.domain.auth.dto;

import com.WhatAreWeDoingNow.eodiga.domain.user.entity.Role;
import lombok.Data;

@Data
public class RegisterDto {
    private String email;
    private String password;
    private String username;
    private Role role;
}