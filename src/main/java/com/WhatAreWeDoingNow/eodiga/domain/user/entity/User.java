package com.WhatAreWeDoingNow.eodiga.domain.user.entity;

import com.WhatAreWeDoingNow.eodiga.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String username;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Store store;

    @Column(nullable = false)
    private int totalMileage = 0;

    public void addMileage(int point) {
        this.totalMileage += point;
    }
}
