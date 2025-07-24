package com.WhatAreWeDoingNow.eodiga.domain.reservation.entity;

import com.WhatAreWeDoingNow.eodiga.domain.review.entity.Review;
import com.WhatAreWeDoingNow.eodiga.domain.user.entity.User;
import com.WhatAreWeDoingNow.eodiga.domain.store.entity.Store;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

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
    private User user;

    @ManyToOne
    private Store store;

    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private Status status; // RESERVED, CANCELED, COMPLETED

    @OneToOne
    @JoinColumn(name = "review_id")
    private Review review;

    public enum Status {
        RESERVED, CANCELED, COMPLETED
    }
}
