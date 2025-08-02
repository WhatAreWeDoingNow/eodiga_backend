package com.WhatAreWeDoingNow.eodiga.domain.reward.controller;

import com.WhatAreWeDoingNow.eodiga.domain.reward.service.RewardService;
import com.WhatAreWeDoingNow.eodiga.global.common.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPath.REWARD_ROOT)
@RequiredArgsConstructor
public class RewardController {
    private final RewardService rewardService;
}
