package com.WhatAreWeDoingNow.eodiga.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import com.WhatAreWeDoingNow.eodiga.domain.user.entity.User;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String detailAddress;

    private String phoneNumber;

    @Column(nullable = false)
    private String category;

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
