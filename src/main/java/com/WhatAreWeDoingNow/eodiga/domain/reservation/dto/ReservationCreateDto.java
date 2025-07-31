package com.WhatAreWeDoingNow.eodiga.domain.reservation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationCreateDto {
    private Long storeId;
    private LocalDateTime dateTime;
}