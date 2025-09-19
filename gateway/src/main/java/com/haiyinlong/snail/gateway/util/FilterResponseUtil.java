package com.haiyinlong.snail.gateway.util;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * FilterResponseUtil
 *
 * @author HaiYinLong
 * @version 2025/09/18 14:19
 **/
public class FilterResponseUtil {

    public static Mono<Void> handleError(ServerWebExchange exchange, String message, HttpStatus code) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String responseBody = String.format("{\"msg\":\"%s\", \"code\":\"%s\"}", message, code.value());
        return response
            .writeWith(Mono.just(response.bufferFactory().wrap(responseBody.getBytes(StandardCharsets.UTF_8))))
            .then(Mono.empty());
    }
}
