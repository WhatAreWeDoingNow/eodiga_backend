package com.WhatAreWeDoingNow.eodiga.domain.userreward.entity;

import com.WhatAreWeDoingNow.eodiga.domain.reward.entity.Reward;
import com.WhatAreWeDoingNow.eodiga.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_rewards")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRewardId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Reward reward;

    @Enumerated(EnumType.STRING)
    private Status status; // USED, PENDING

    private LocalDateTime date;

    public enum Status {
        USED, PENDING
    }
}
