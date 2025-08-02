package com.WhatAreWeDoingNow.eodiga.domain.ai.controller;

import com.WhatAreWeDoingNow.eodiga.domain.ai.dto.CreateCommentDto;
import com.WhatAreWeDoingNow.eodiga.domain.ai.service.AiService;
import com.WhatAreWeDoingNow.eodiga.global.common.ApiPath;
import com.WhatAreWeDoingNow.eodiga.global.common.dto.Response;
import com.WhatAreWeDoingNow.eodiga.global.utility.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPath.Ai_ROOT)
@RequiredArgsConstructor
public class AiController {
    private final AiService aiService;

    public ResponseEntity<Response<Object>> createComment(CreateCommentDto dto) {
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), aiService.createComment(dto), "AI를 통해 한줄 멘트 생성하였습니다."));
    }
}
