package com.WhatAreWeDoingNow.eodiga.domain.content.entity;

import com.WhatAreWeDoingNow.eodiga.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contents")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;

    @ManyToOne
    private Store store;

    @Enumerated(EnumType.STRING)
    private ContentType type; // CARD_NEWS, VIDEO

    private String title;

    private String contentUrl;

    private LocalDateTime createdAt;

    public enum ContentType {
        CARD_NEWS, VIDEO
    }
}
