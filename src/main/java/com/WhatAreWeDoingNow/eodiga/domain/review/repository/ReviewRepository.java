package com.WhatAreWeDoingNow.eodiga.domain.review.repository;

import com.WhatAreWeDoingNow.eodiga.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByStore_StoreId(Long storeId);

    Optional<Review> findByReservation_ReservationId(Long reservationId);
}