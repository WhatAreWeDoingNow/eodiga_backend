package com.WhatAreWeDoingNow.eodiga.domain.mileage.service;


import com.WhatAreWeDoingNow.eodiga.domain.mileage.dto.MileageRequestDto;
import com.WhatAreWeDoingNow.eodiga.domain.mileage.dto.MileageResponseDto;
import com.WhatAreWeDoingNow.eodiga.domain.mileage.entity.Mileage;
import com.WhatAreWeDoingNow.eodiga.domain.mileage.entity.Type;
import com.WhatAreWeDoingNow.eodiga.domain.mileage.repository.MileageRepository;
import com.WhatAreWeDoingNow.eodiga.domain.user.entity.User;
import com.WhatAreWeDoingNow.eodiga.domain.user.repository.UserRepository;
import com.WhatAreWeDoingNow.eodiga.global.utility.JwtProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MileageService {

    private final MileageRepository mileageRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public void handleMileage(MileageRequestDto dto, String token) {
        User user = userRepository.findByEmail(jwtProvider.extractEmail(token))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // 적립은 +, 사용/기부는 - 처리
        int pointChange = (dto.getType() == Type.EARNED) ? dto.getPoint() : -dto.getPoint();

        // User의 총 마일리지 업데이트
        user.addMileage(pointChange);
        userRepository.save(user);

        Mileage mileage = Mileage.builder()
                .user(user)
                .point(dto.getPoint())
                .type(dto.getType())
                .description(dto.getDescription())
                .createdAt(LocalDateTime.now())
                .build();

        mileageRepository.save(mileage);
    }

    @Transactional(readOnly = true)
    public MileageResponseDto getTotalMileage(String token) {
        User user = userRepository.findByEmail(jwtProvider.extractEmail(token))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new MileageResponseDto(user.getTotalMileage());
    }
}
