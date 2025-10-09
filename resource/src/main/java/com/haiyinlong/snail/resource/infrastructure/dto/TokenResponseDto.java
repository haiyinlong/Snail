package com.haiyinlong.snail.resource.infrastructure.dto;

import lombok.Data;

/**
 * TokenResponseDto
 *
 * @author HaiYinLong
 * @version 2025/09/26 16:37
 **/
@Data
public class TokenResponseDto {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String scope;
    private int expires_in;
    private String id_token;
}
