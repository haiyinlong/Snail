package com.haiyinlong.snail.authorization.interfaces.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haiyinlong.snail.authorization.application.dto.GetTokenResponse;
import com.haiyinlong.snail.authorization.application.service.ClientLoginService;
import com.haiyinlong.snail.authorization.infrastructure.dto.ResponseDto;

import lombok.RequiredArgsConstructor;

/**
 * ClientLoginController
 *
 * @author HaiYinLong
 * @version 2025/09/26 15:28
 **/
@RequestMapping("/client")
@RestController
@RequiredArgsConstructor
public class ClientLoginController {
    private final ClientLoginService clientLoginService;

    @GetMapping("/token")
    public ResponseDto<GetTokenResponse> getToken(@RequestParam String clientId, @RequestParam String code,
        @RequestParam String redirectUri) {
        GetTokenResponse token = clientLoginService.getToken(clientId, code, redirectUri);
        return ResponseDto.of(token);
    }
}
