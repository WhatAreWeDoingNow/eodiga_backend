package com.WhatAreWeDoingNow.eodiga.domain.event.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String title;

    private String description;

    private String region;

    private LocalDate startDate;

    private LocalDate endDate;

    private String imageUrl;
}
