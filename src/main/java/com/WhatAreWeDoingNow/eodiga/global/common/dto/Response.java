package com.WhatAreWeDoingNow.eodiga.global.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private int status;
    private T data;
    private String message;
}