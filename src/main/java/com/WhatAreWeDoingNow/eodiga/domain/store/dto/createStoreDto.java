package com.WhatAreWeDoingNow.eodiga.domain.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class createStoreDto {
    private String name;
    private String address;
    private String detailAddress;
    private String phoneNumber;
    private String category;
}
