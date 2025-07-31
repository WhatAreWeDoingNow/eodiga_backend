package com.WhatAreWeDoingNow.eodiga.domain.store.service;

import com.WhatAreWeDoingNow.eodiga.domain.store.dto.CreateStoreDto;
import com.WhatAreWeDoingNow.eodiga.domain.store.dto.StoreResponseDto;
import com.WhatAreWeDoingNow.eodiga.domain.store.dto.StoreUpdateDto;
import com.WhatAreWeDoingNow.eodiga.domain.store.entity.Store;
import com.WhatAreWeDoingNow.eodiga.domain.store.repository.StoreRepository;
import com.WhatAreWeDoingNow.eodiga.domain.user.entity.Role;
import com.WhatAreWeDoingNow.eodiga.domain.user.entity.User;
import com.WhatAreWeDoingNow.eodiga.domain.user.repository.UserRepository;
import com.WhatAreWeDoingNow.eodiga.global.utility.JwtProvider;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public void registerStore(String token, CreateStoreDto dto) {
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

    public StoreResponseDto getMyStore(String token) {
        String email = jwtProvider.extractEmail(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Store store = storeRepository.findByOwner(user)
                .orElseThrow(() -> new NoSuchElementException("내 가게 정보가 없습니다."));

        return convertToDto(store);
    }

    public List<StoreResponseDto> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public StoreResponseDto getStoreById(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new NoSuchElementException("해당 가게를 찾을 수 없습니다."));

        return convertToDto(store);
    }

    private StoreResponseDto convertToDto(Store store) {
        return new StoreResponseDto(
                store.getStoreId(),
                store.getName(),
                store.getAddress(),
                store.getDetailAddress(),
                store.getPhoneNumber(),
                store.getCategory(),
                store.getOwner().getUserId()
        );
    }

    @Transactional
    public Store updateStore(Long storeId, StoreUpdateDto dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + storeId));

        if (dto.getName() != null) store.setName(dto.getName());
        if (dto.getAddress() != null) store.setAddress(dto.getAddress());
        if (dto.getDetailAddress() != null) store.setDetailAddress(dto.getDetailAddress());
        if (dto.getPhoneNumber() != null) store.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getCategory() != null) store.setCategory(dto.getCategory());

        return store;
    }

    public void deleteStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + storeId));
        storeRepository.delete(store);
    }
}
