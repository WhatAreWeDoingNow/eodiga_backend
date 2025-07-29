package com.WhatAreWeDoingNow.eodiga.domain.store.controller;

import com.WhatAreWeDoingNow.eodiga.domain.store.dto.createStoreDto;
import com.WhatAreWeDoingNow.eodiga.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerStore(
            @RequestHeader("Authorization") String token,
            @RequestBody createStoreDto dto) {

        token = token.replace("Bearer ", ""); // "Bearer " 제거
        storeService.registerStore(token, dto);

        return ResponseEntity.ok("가게가 성공적으로 등록되었습니다.");
    }
}
