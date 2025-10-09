package com.haiyinlong.snail.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * SecurityConfig
 *
 * @author HaiYinLong
 * @version 2025/09/18 15:09
 **/
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(auth -> auth
            .pathMatchers("/auth/oauth2/authorize", "/auth/oauth2/token", "/auth/login", "/auth/error",
                "/auth/connect/logout")
            .permitAll().pathMatchers("/actuator/**").permitAll()
            // 获取token
            .pathMatchers("/resource/client/token").permitAll().anyExchange().authenticated())
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
            }));
        return http.build();
    }
}
