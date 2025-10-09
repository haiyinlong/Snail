package com.haiyinlong.snail.resource.infrastructure.fegin;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.haiyinlong.snail.resource.infrastructure.dto.TokenResponseDto;

/**
 * AuthFeignClient
 *
 * @author HaiYinLong
 * @version 2025/09/26 16:03
 **/
@FeignClient(name = "snail-auth")
public interface AuthFeignClient {

    @PostMapping(path = "/oauth2/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenResponseDto getToken(@RequestHeader(name = "Authorization") String authorization,
        @RequestBody Map<String, String> formParam
    // @RequestParam("grant_type") String grantType, @RequestParam("client_id") String clientId,
    // @RequestParam("code") String code, @RequestParam("redirect_uri") String redirectUri
    );
}
