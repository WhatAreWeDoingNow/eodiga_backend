package com.WhatAreWeDoingNow.eodiga.domain.store.dto;

import lombok.Data;

@Data
public class StoreUpdateDto {
    private String name;
    private String address;
    private String detailAddress;
    private String phoneNumber;
    private String category;
}
