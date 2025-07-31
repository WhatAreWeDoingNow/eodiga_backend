package com.WhatAreWeDoingNow.eodiga.domain.reservation.controller;

import com.WhatAreWeDoingNow.eodiga.domain.reservation.dto.ReservationCreateDto;
import com.WhatAreWeDoingNow.eodiga.domain.reservation.dto.ReservationResponseDto;
import com.WhatAreWeDoingNow.eodiga.domain.reservation.dto.ReservationStatusUpdateDto;
import com.WhatAreWeDoingNow.eodiga.domain.reservation.entity.Reservation;
import com.WhatAreWeDoingNow.eodiga.domain.reservation.service.ReservationService;
import com.WhatAreWeDoingNow.eodiga.global.common.ApiPath;
import com.WhatAreWeDoingNow.eodiga.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPath.RESERVATION_ROOT)
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Response<Reservation>> createReservation(@RequestBody ReservationCreateDto dto, @RequestHeader("Authorization") String token) {
        Reservation reservation = reservationService.createReservation(dto,token);
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), reservation, "가게 예약이 완료 되었습니다."));
    }

    @GetMapping
    public ResponseEntity<Response<List<ReservationResponseDto>>> getReservations(@RequestHeader("Authorization") String token) {
        List<ReservationResponseDto> reservations = reservationService.getReservationsByUserId(token);
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), reservations, "가게 예약 목록을 가져왔습니다."));
    }

    @PatchMapping("/{reservationId}/status")
    public ResponseEntity<Response<Void>> updateStatus(
            @PathVariable Long reservationId,
            @RequestBody ReservationStatusUpdateDto dto
    ) {
        reservationService.updateReservationStatus(reservationId, dto.getStatus());
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), null, "예약 상태가 변경되었습니다."));
    }
}
