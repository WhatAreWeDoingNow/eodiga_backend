package com.WhatAreWeDoingNow.eodiga.domain.ai.service;

import com.WhatAreWeDoingNow.eodiga.domain.ai.dto.CreateCommentDto;
import com.WhatAreWeDoingNow.eodiga.global.utility.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {
    private final RestTemplateUtil restTemplateUtil;

    public Object createComment(CreateCommentDto dto) {
        String url = "http://localhost:8000/api/v1/textGenerate";
        return restTemplateUtil.post(url, dto, CreateCommentDto.class);
    }
}
