package com.WhatAreWeDoingNow.eodiga.global.common;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiPath {
    public static final String version = "/v1";
    public static final String BASE_PATH = "/api" + version;
    public static final String AUTH_ROOT = BASE_PATH + "/auth";
    public static final String STORE_ROOT = BASE_PATH + "/store";

    public static String H2_PATH;

    @Value("${spring.datasource.h2.console.path}")
    private String h2Path;

    @PostConstruct
    public void init() {
        H2_PATH = h2Path;
    }
}