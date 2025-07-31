package com.WhatAreWeDoingNow.eodiga.domain.store.controller;

import com.WhatAreWeDoingNow.eodiga.domain.store.dto.CreateStoreDto;
import com.WhatAreWeDoingNow.eodiga.domain.store.dto.StoreResponseDto;
import com.WhatAreWeDoingNow.eodiga.domain.store.entity.Store;
import com.WhatAreWeDoingNow.eodiga.domain.store.service.StoreService;
import com.WhatAreWeDoingNow.eodiga.global.common.ApiPath;
import com.WhatAreWeDoingNow.eodiga.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPath.STORE_ROOT)
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/register")
    public ResponseEntity<Response<String>> registerStore(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateStoreDto dto) {

        token = token.replace("Bearer ", "");
        storeService.registerStore(token, dto);

        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), null, "가게가 성공적으로 등록되었습니다.");
    }

    @GetMapping("/me")
    public ResponseEntity<Response<StoreResponseDto>> getMyStore(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), storeService.getMyStore(token), "내 가게 정보를 가져왔습니다."));
    }

    @GetMapping
    public ResponseEntity<Response<List<StoreResponseDto>>> getAllStores() {
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), storeService.getAllStores(), "전체 가게 정보를 가져왔습니다."));
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<Response<StoreResponseDto>> getStoreById(@PathVariable Long storeId) {
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), storeService.getStoreById(storeId), "해당 storeId로 가게 정보를 가져왔습니다."));
    }


}
