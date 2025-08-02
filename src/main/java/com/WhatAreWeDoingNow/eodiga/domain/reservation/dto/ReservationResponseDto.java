package com.WhatAreWeDoingNow.eodiga.domain.reservation.dto;

import com.WhatAreWeDoingNow.eodiga.domain.reservation.entity.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class ReservationResponseDto {
    private Long reservationId;
    private String storeName;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private Status status;
}