package com.WhatAreWeDoingNow.eodiga.domain.reward.service;

import com.WhatAreWeDoingNow.eodiga.domain.reward.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewardService {
    private final RewardRepository rewardRepository;
}
