package com.WhatAreWeDoingNow.eodiga.domain.review.dto;

import lombok.Data;

@Data
public class CreateReviewDto {
    Long reservationId;
    String content;
    int rating;
}
