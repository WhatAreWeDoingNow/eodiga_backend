package com.WhatAreWeDoingNow.eodiga.domain.store.service;

import com.WhatAreWeDoingNow.eodiga.domain.store.dto.createStoreDto;
import com.WhatAreWeDoingNow.eodiga.domain.store.entity.Store;
import com.WhatAreWeDoingNow.eodiga.domain.store.repository.StoreRepository;
import com.WhatAreWeDoingNow.eodiga.domain.user.entity.Role;
import com.WhatAreWeDoingNow.eodiga.domain.user.entity.User;
import com.WhatAreWeDoingNow.eodiga.domain.user.repository.UserRepository;
import com.WhatAreWeDoingNow.eodiga.global.utility.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public void registerStore(String token, createStoreDto dto) {
        String email = jwtProvider.extractEmail(token); // 이메일 추출

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        if (user.getRole() != Role.OWNER) {
            throw new IllegalStateException("가게 등록은 OWNER 권한만 가능합니다.");
        }

        if (user.getStore() != null) {
            throw new IllegalStateException("이미 가게가 등록되어 있습니다.");
        }

        Store store = Store.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .detailAddress(dto.getDetailAddress())
                .phoneNumber(dto.getPhoneNumber())
                .category(dto.getCategory())
                .owner(user)
                .build();

        storeRepository.save(store);
    }


}