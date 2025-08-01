package com.WhatAreWeDoingNow.eodiga.domain.reservation.dto;

import com.WhatAreWeDoingNow.eodiga.domain.reservation.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationStatusUpdateDto {
    private Status status;
}