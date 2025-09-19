package com.haiyinlong.snail.authentication.application.client.dto;

import java.util.List;

import lombok.Data;

/**
 * CreateRegisteredClientRequest
 *
 * @author HaiYinLong
 * @version 2025/09/16 11:58
 **/
@Data
public class CreateRegisteredClientRequest {
    private String clientName;
    private String description;
    private List<String> clientAuthenticationMethods;
    private List<String> authorizationGrantTypes;
    private List<String> redirectUris;
    private List<String> postLogoutRedirectUris;
    private List<String> scopes;
    private boolean requireAuthorizationConsent;
    private String clientSettings;
    private String tokenSettings;
}
