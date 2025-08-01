package com.WhatAreWeDoingNow.eodiga.domain.review.controller;

import com.WhatAreWeDoingNow.eodiga.domain.review.dto.CreateReviewDto;
import com.WhatAreWeDoingNow.eodiga.domain.review.entity.Review;
import com.WhatAreWeDoingNow.eodiga.domain.review.service.ReviewService;
import com.WhatAreWeDoingNow.eodiga.global.common.ApiPath;
import com.WhatAreWeDoingNow.eodiga.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPath.REVIEW_ROOT)
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping()
    public ResponseEntity<Response<String>> registerReview(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateReviewDto createReviewDto) {
        token = token.replace("Bearer", "");
        reviewService.register(createReviewDto, token);
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), null, "리뷰가 등록되었습니다."));
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<Response<List<Review>>> getReviews(@PathVariable("storeId") Long storeId) {
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), reviewService.getReviews(storeId), "가게 리뷰를 조회하였습니다."));
    }
}
