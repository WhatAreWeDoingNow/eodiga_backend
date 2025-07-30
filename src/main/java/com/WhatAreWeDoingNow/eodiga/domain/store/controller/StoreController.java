package com.WhatAreWeDoingNow.eodiga.domain.store.controller;

import com.WhatAreWeDoingNow.eodiga.domain.store.dto.CreateStoreDto;
import com.WhatAreWeDoingNow.eodiga.domain.store.dto.StoreResponseDto;
import com.WhatAreWeDoingNow.eodiga.domain.store.entity.Store;
import com.WhatAreWeDoingNow.eodiga.domain.store.service.StoreService;
import com.WhatAreWeDoingNow.eodiga.global.common.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPath.STORE_ROOT)
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerStore(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateStoreDto dto) {

        token = token.replace("Bearer ", "");
        storeService.registerStore(token, dto);

        return ResponseEntity.ok("가게가 성공적으로 등록되었습니다.");
    }

    @GetMapping("/me")
    public ResponseEntity<StoreResponseDto> getMyStore(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return ResponseEntity.ok(storeService.getMyStore(token));
    }

    @GetMapping
    public ResponseEntity<List<StoreResponseDto>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto> getStoreById(@PathVariable Long storeId) {
        return ResponseEntity.ok(storeService.getStoreById(storeId));
    }


}
