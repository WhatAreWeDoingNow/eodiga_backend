package com.WhatAreWeDoingNow.eodiga.domain.review.service;

import com.WhatAreWeDoingNow.eodiga.domain.reservation.entity.Reservation;
import com.WhatAreWeDoingNow.eodiga.domain.reservation.repository.ReservationRepository;
import com.WhatAreWeDoingNow.eodiga.domain.review.dto.CreateReviewDto;
import com.WhatAreWeDoingNow.eodiga.domain.review.entity.Review;
import com.WhatAreWeDoingNow.eodiga.domain.review.repository.ReviewRepository;
import com.WhatAreWeDoingNow.eodiga.domain.user.entity.User;
import com.WhatAreWeDoingNow.eodiga.domain.user.repository.UserRepository;
import com.WhatAreWeDoingNow.eodiga.global.utility.JwtProvider;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public void register(CreateReviewDto createReviewDto, String token) {
        String email = jwtProvider.extractEmail(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Reservation reservation = reservationRepository.findById(createReviewDto.getReservationId())
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        if (reservation.getReview() != null) {
            throw new IllegalStateException("Review already exists for this reservation");
        }

        Review review = Review.builder()
                .user(user)
                .reservation(reservation)
                .content(createReviewDto.getContent())
                .rating(createReviewDto.getRating())
                .build();

        reviewRepository.save(review);
        reservation.setReview(review);
    }

}
