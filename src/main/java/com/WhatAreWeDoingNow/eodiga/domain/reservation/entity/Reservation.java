package com.WhatAreWeDoingNow.eodiga.domain.reservation.entity;

import com.WhatAreWeDoingNow.eodiga.domain.user.entity.User;
import com.WhatAreWeDoingNow.eodiga.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(nullable = false)
    private LocalDate reservationDate;

    @Column(nullable = false)
    private LocalTime reservationTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private String reserverName;

    @Column(nullable = false)
    private int peopleCount;
}
