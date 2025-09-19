package com.haiyinlong.snail.gateway.filter;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * JwtUserInfoFilter
 *
 * @author HaiYinLong
 * @version 2025/09/18 17:23
 **/
@Component
@Slf4j
public class JwtUserInfoFilter implements GlobalFilter, Ordered {
    public static final String USER_ID_HEADER = "user-id";
    public static final String USERNAME_HEADER = "username";
    public static final String DEPT_ID_HEADER = "dept-id";
    public static final String DEPT_NAME_HEADER = "dept-name";
    public static final String USER_ROLES_HEADER = "user-roles";
    public static final String USER_EMAIL_HEADER = "user-email";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return exchange.getPrincipal().filter(principal -> principal instanceof JwtAuthenticationToken)
            .cast(JwtAuthenticationToken.class).flatMap(jwtAuth -> {
                Jwt jwt = jwtAuth.getToken();
                ServerWebExchange newExchange = addUserInfoHeaders(exchange, jwt);
                return chain.filter(newExchange);
            }).then(chain.filter(exchange)).onErrorResume(error -> {
                log.error("Failed to extract user info from JWT", error);
                return chain.filter(exchange);
            });
    }

    private ServerWebExchange addUserInfoHeaders(ServerWebExchange exchange, Jwt jwt) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders -> {
            // 用户基本信息
            addHeaderIfPresent(httpHeaders, USER_ID_HEADER, jwt.getSubject());
            addHeaderIfPresent(httpHeaders, USERNAME_HEADER, jwt.getClaimAsString("username"));
            // 部门信息
            addHeaderIfPresent(httpHeaders, DEPT_ID_HEADER, jwt.getClaimAsString("dept_id"));
            addHeaderIfPresent(httpHeaders, DEPT_NAME_HEADER, jwt.getClaimAsString("dept_name"));
            // 角色信息
            List<String> roles = jwt.getClaimAsStringList("roles");
            if (roles != null && !roles.isEmpty()) {
                httpHeaders.add(USER_ROLES_HEADER, String.join(",", roles));
            }
            // 其他自定义信息
            addHeaderIfPresent(httpHeaders, USER_EMAIL_HEADER, jwt.getClaimAsString("email"));
        }).build();
        return exchange.mutate().request(serverHttpRequest).build();
    }

    private void addHeaderIfPresent(HttpHeaders headers, String headerName, String value) {
        if (value != null && !value.isEmpty()) {
            headers.add(headerName, value);
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1000;
    }
}
