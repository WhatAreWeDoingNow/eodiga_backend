package com.WhatAreWeDoingNow.eodiga.domain.mileage.controller;

import com.WhatAreWeDoingNow.eodiga.domain.mileage.dto.MileageRequestDto;
import com.WhatAreWeDoingNow.eodiga.domain.mileage.dto.MileageResponseDto;
import com.WhatAreWeDoingNow.eodiga.domain.mileage.service.MileageService;
import com.WhatAreWeDoingNow.eodiga.global.common.ApiPath;
import com.WhatAreWeDoingNow.eodiga.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPath.MILEAGE_ROOT)
@RequiredArgsConstructor
public class MileageController {
    private final MileageService mileageService;

    @PostMapping
    public ResponseEntity<Response<Void>> handleMileage(@RequestBody MileageRequestDto dto, @RequestHeader("Authorization") String token) {
        mileageService.handleMileage(dto, token);
        return ResponseEntity.ok(new Response<>(200, null, "마일리지가 처리되었습니다."));
    }

    @GetMapping
    public ResponseEntity<Response<MileageResponseDto>> getTotalMileage(@RequestHeader("Authorization") String token) {
        MileageResponseDto responseDto = mileageService.getTotalMileage( token);
        return ResponseEntity.ok(new Response<>(200, responseDto, "총 마일리지 조회 성공"));
    }
}
