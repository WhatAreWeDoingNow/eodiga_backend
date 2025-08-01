package com.WhatAreWeDoingNow.eodiga.domain.review.repository;

import com.WhatAreWeDoingNow.eodiga.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
