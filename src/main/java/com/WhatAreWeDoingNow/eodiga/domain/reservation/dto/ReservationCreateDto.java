package com.WhatAreWeDoingNow.eodiga.domain.reservation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ReservationCreateDto {
    private Long storeId;
    private LocalDate reservationDate;
    private LocalTime reservationTime;

    private String reserverName;
    private int peopleCount;
}
