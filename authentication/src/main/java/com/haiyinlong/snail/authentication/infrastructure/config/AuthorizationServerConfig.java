package com.haiyinlong.snail.authentication.infrastructure.config;

import java.io.InputStream;
import java.security.KeyStore;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.haiyinlong.snail.authentication.infrastructure.security.CustomOidcUserInfoMapper;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import lombok.RequiredArgsConstructor;

/****
 * AuthorizationServerConfig****
 *
 * @author HaiYinLong
 * @version 2025/09/16 17:51
 **/
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig {

    private final DataSource dataSource;
    private final CustomOidcUserInfoMapper customOidcUserInfoMapper;
    @Value("${spring.security.oauth2.authorizationserver.issuer:http://localhost:9000}")
    private String issuerUrl;

    @Value("${spring.security.oauth2.authorizationserver.jks.path:snail-auth-server-dev.jks}")
    private String jksPath;

    @Value("${spring.security.oauth2.authorizationserver.jks.password:snail-ken}")
    private String jksPassword;

    @Value("${spring.security.oauth2.authorizationserver.jks.alias:snail-auth-server}")
    private String jksAlias;

    @Bean
    @Primary
    public RegisteredClientRepository registeredClientRepository() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }

    @Bean
    public OAuth2AuthorizationService authorizationService() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository());
    }

    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository());
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    /**
     * 生成固定证书,防止每次启动导致密钥变化
     *
     * @return JWKSource
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = loadRsaKey();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().issuer(issuerUrl).build();
    }

    /**
     * OAuth2 Authorization Server 安全配置
     */
    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
            OAuth2AuthorizationServerConfigurer.authorizationServer();
        http.securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
            .with(authorizationServerConfigurer, Customizer.withDefaults()).authorizeHttpRequests(
                (authorize) -> authorize.requestMatchers("/connect/logout").permitAll().anyRequest().authenticated());
        // 配置OpenID Connect并自定义UserInfo端点
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(oidc -> {
            oidc.clientRegistrationEndpoint(Customizer.withDefaults());
            // 自定义UserInfo端点响应
            oidc.userInfoEndpoint(userInfo -> userInfo.userInfoMapper(customOidcUserInfoMapper::apply));
        });
        http.exceptionHandling(
            exceptions -> exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")));
        return http.build();
    }

    private RSAKey loadRsaKey() {
        try {
            ClassPathResource jksResource = new ClassPathResource(jksPath);
            KeyStore keyStore = KeyStore.getInstance("JKS");
            try (InputStream is = jksResource.getInputStream()) {
                keyStore.load(is, jksPassword.toCharArray());
            }
            return RSAKey.load(keyStore, jksAlias, jksPassword.toCharArray());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
