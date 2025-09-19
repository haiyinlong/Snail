package com.haiyinlong.snail.authentication.application.client;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haiyinlong.snail.authentication.application.client.dto.CreateRegisteredClientRequest;
import com.haiyinlong.snail.authentication.domain.client.RegisteredClientAggregate;
import com.haiyinlong.snail.authentication.domain.client.repository.RegisteredClientRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * CreateRegisteredClientService
 *
 * @author HaiYinLong
 * @version 2025/09/16 11:47
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class CreateRegisteredClientService {

    private final RegisteredClientRepository registeredClientRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createRegisteredClient(CreateRegisteredClientRequest createRequest) {
        RegisteredClientAggregate registeredClientAggregate =
            RegisteredClientAggregate.create(createRequest.getClientName());
        registeredClientAggregate.initRegisterClient(createRequest.getDescription(),
            createRequest.getClientAuthenticationMethods(), createRequest.getAuthorizationGrantTypes(),
            createRequest.getScopes(), createRequest.getPostLogoutRedirectUris(), createRequest.getRedirectUris(),
            createRequest.isRequireAuthorizationConsent(), createRequest.getClientSettings(),
            createRequest.getTokenSettings());
        registeredClientAggregate.generateClientIdAndSecret();
        registeredClientRepository.save(registeredClientAggregate);
    }

    /**
     *
     * @param clientId clientId
     * @param rawClientSecret rawClientSecret
     */
    public static void printClientBasic(String clientId, String rawClientSecret) {
        // 1. 拼接 "client_id:client_secret"
        String credentials = clientId + ":" + rawClientSecret;
        // 2. UTF-8 编码后 Base64 编码
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        // 3. 生成最终的 Authorization Header
        String basicAuthHeader = "Basic " + encodedCredentials;
        log.info("Credentials: {}", credentials);
        log.info("Base64: {}", encodedCredentials);
        log.info("Authorization Header: {}", basicAuthHeader);
    }

    public static void main(String[] args) {
        CreateRegisteredClientService.printClientBasic("6e0353d6-6335-4302-8edb-3f561f99c045",
            "0bd44bf1-ce86-43a0-961a-50f6a5b5017b");
    }
}
