package com.haiyinlong.snail.authorization.application.dto;

import lombok.Data;

/**
 * GetTokenResponse
 *
 * @author HaiYinLong
 * @version 2025/09/26 15:38
 **/
@Data
public class GetTokenResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String scope;
    private int expires_in;
    private String id_token;
}
