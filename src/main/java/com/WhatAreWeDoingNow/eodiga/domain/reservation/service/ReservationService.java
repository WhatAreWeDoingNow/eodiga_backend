package com.WhatAreWeDoingNow.eodiga.domain.reservation.service;

import com.WhatAreWeDoingNow.eodiga.domain.reservation.dto.ReservationCreateDto;
import com.WhatAreWeDoingNow.eodiga.domain.reservation.dto.ReservationResponseDto;
import com.WhatAreWeDoingNow.eodiga.domain.reservation.entity.Reservation;
import com.WhatAreWeDoingNow.eodiga.domain.reservation.entity.Status;
import com.WhatAreWeDoingNow.eodiga.domain.reservation.repository.ReservationRepository;
import com.WhatAreWeDoingNow.eodiga.domain.store.entity.Store;
import com.WhatAreWeDoingNow.eodiga.domain.store.repository.StoreRepository;
import com.WhatAreWeDoingNow.eodiga.domain.user.entity.User;
import com.WhatAreWeDoingNow.eodiga.domain.user.repository.UserRepository;
import com.WhatAreWeDoingNow.eodiga.global.utility.JwtProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public Reservation createReservation(ReservationCreateDto dto, String token) {
        User user = userRepository.findByEmail(jwtProvider.extractEmail(token))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException("Store not found"));

        Reservation reservation = Reservation.builder()
                .user(user)
                .store(store)
                .dateTime(dto.getDateTime())
                .status(Status.RESERVED)
                .build();

        return reservationRepository.save(reservation);
    }

    @Transactional(readOnly = true)
    public List<ReservationResponseDto> getReservationsByUserId(String token) {
        User user = userRepository.findByEmail(jwtProvider.extractEmail(token))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return reservationRepository.findAllByUser(user).stream()
                .map(res -> ReservationResponseDto.builder()
                        .reservationId(res.getReservationId())
                        .storeName(res.getStore().getName())
                        .dateTime(res.getDateTime())
                        .status(res.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
}
