package com.WhatAreWeDoingNow.eodiga.domain.store.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreResponseDto {
    private Long storeId;
    private String name;
    private String address;
    private String detailAddress;
    private String phoneNumber;
    private String category;
    private Long ownerId;
}
