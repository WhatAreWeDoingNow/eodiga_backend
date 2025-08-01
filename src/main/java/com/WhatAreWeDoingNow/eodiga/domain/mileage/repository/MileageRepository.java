package com.WhatAreWeDoingNow.eodiga.domain.mileage.repository;

import com.WhatAreWeDoingNow.eodiga.domain.mileage.entity.Mileage;
import com.WhatAreWeDoingNow.eodiga.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MileageRepository extends JpaRepository<Mileage, Long> {
    List<Mileage> findAllByUser(User user);
}
