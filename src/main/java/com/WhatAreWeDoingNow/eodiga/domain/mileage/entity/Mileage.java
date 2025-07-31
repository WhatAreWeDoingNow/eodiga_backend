package com.WhatAreWeDoingNow.eodiga.domain.mileage.entity;

import com.WhatAreWeDoingNow.eodiga.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mileages")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Mileage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mileageId;

    @ManyToOne
    private User user;

    private int point;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String description;

    private LocalDateTime createdAt;

}
