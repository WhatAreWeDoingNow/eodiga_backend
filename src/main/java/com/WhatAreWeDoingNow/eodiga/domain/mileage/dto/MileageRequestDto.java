package com.WhatAreWeDoingNow.eodiga.domain.mileage.dto;

import com.WhatAreWeDoingNow.eodiga.domain.mileage.entity.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MileageRequestDto {
    private int point;
    private Type type;
    private String description;
}