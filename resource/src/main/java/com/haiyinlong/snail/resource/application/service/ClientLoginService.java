package com.haiyinlong.snail.resource.application.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.haiyinlong.snail.resource.application.dto.GetTokenResponse;
import com.haiyinlong.snail.resource.infrastructure.dto.TokenResponseDto;
import com.haiyinlong.snail.resource.infrastructure.fegin.AuthFeignClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ClientLoginService
 *
 * @author HaiYinLong
 * @version 2025/09/26 15:30
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientLoginService {
    private final AuthFeignClient authFeignClient;

    public GetTokenResponse getToken(String clientId, String code, String redirectUri) {
        // 1. 拼接 "client_id:client_secret"
        String credentials = clientId + ":" + "13af5e44-ba3e-45c5-a1b1-9ecc268b93c8";
        // 2. UTF-8 编码后 Base64 编码
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        // 3. 生成最终的 Authorization Header
        String basicAuthHeader = "Basic " + encodedCredentials;
        // 计算base64
        TokenResponseDto token = null;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("grant_type", "authorization_code");
            params.put("client_id", clientId);
            params.put("code", code);
            params.put("redirect_uri", redirectUri);

            token = authFeignClient.getToken(basicAuthHeader, params);
            // 获取client信息
            GetTokenResponse getTokenResponse = new GetTokenResponse();
            getTokenResponse.setAccess_token(token.getAccess_token());
            getTokenResponse.setRefresh_token(token.getRefresh_token());
            getTokenResponse.setId_token(token.getId_token());
            getTokenResponse.setToken_type(token.getToken_type());
            getTokenResponse.setScope(token.getScope());
            getTokenResponse.setExpires_in(token.getExpires_in());
            return getTokenResponse;
        } catch (Exception e) {
            log.error("获取token异常", e);
            throw new RuntimeException(e);
        }
    }
}
