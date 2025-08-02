package com.WhatAreWeDoingNow.eodiga.domain.reward.repository;

import com.WhatAreWeDoingNow.eodiga.domain.reward.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<Reward, Integer> {
}
