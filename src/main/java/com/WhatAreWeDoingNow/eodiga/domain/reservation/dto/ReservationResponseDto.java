package com.WhatAreWeDoingNow.eodiga.domain.reservation.dto;

import com.WhatAreWeDoingNow.eodiga.domain.reservation.entity.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReservationResponseDto {
    private Long reservationId;
    private String storeName;
    private LocalDateTime dateTime;
    private Status status;
}