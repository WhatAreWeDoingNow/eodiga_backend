package com.WhatAreWeDoingNow.eodiga.domain.store.repository;

import com.WhatAreWeDoingNow.eodiga.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
