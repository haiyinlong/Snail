package com.haiyinlong.snail.authentication.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
            // OAuth2 相关端点不需要认证
            .requestMatchers("/oauth2/authorize").permitAll().requestMatchers("/oauth2/token").permitAll()
            .requestMatchers("/oauth2/jwks").permitAll().requestMatchers("/oauth2/.well-known/**").permitAll()
            // 登录和错误页面
            .requestMatchers("/login", "/error", "/connect/logout", "/").permitAll()
            .requestMatchers("/assets/**", "/css/**", "/fonts/**", "/js/**", "/images/**", "/favicon.ico",
                "/default-ui.css")
            .permitAll()
            // 其他所有请求需要认证
            .anyRequest().authenticated()).oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
            }) // 使用 JWT 验证 access_token
        )
            // 登录
            .formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/index")
                .failureUrl("/login?error").permitAll())
            .httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.requireCsrfProtectionMatcher(request -> {
                // 只对非 OAuth2 token 端点的 form 请求启用 CSRF
                String path = request.getRequestURI();
                // 增加context path的处理，使路径匹配更准确
                String contextPath = request.getContextPath();
                if (path.startsWith(contextPath)) {
                    path = path.substring(contextPath.length());
                }

                // 匹配不需要CSRF保护的路径
                if (path.equals("/oauth2/token") || path.equals("/token")) {
                    return false;
                }
                return request.getMethod().equals("POST")
                    && request.getContentType().equals(MediaType.APPLICATION_FORM_URLENCODED);
            }));
        return http.build();
    }
}
