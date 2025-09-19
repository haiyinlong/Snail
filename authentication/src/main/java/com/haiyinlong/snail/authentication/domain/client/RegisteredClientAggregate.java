package com.haiyinlong.snail.authentication.domain.client;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import lombok.Getter;

/**
 * RegisteredClientAggregate
 *
 * @author HaiYinLong
 * @version 2025/09/16 11:48
 **/
@Getter
public class RegisteredClientAggregate {
    private Integer id;
    private String description;
    private String clientId;
    private String clientSecret;
    private String rawClientSecret;
    private String clientName;
    private String clientAuthenticationMethods;
    private String authorizationGrantTypes;
    private String redirectUris;
    private String postLogoutRedirectUris;
    private String scopes;
    private boolean requireAuthorizationConsent;
    private String clientSettings;
    private String tokenSettings;
    private Integer enabledFlag;
    private final Integer deletedFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 私有构造函数，防止直接实例化
    private RegisteredClientAggregate() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        this.enabledFlag = 1;
        this.deletedFlag = 0;
    }

    /**
     * 创建带有基本信息的客户端注册聚合根
     */
    public static RegisteredClientAggregate create(String clientName) {
        RegisteredClientAggregate aggregate = new RegisteredClientAggregate();
        aggregate.clientName = clientName;
        return aggregate;
    }

    public static RegisteredClientAggregate of(Integer id, String clientId, String clientName, String clientSecret,
        String clientAuthenticationMethods, String authorizationGrantTypes, String scopes, String redirectUris,
        String postLogoutRedirectUris, boolean requireAuthorizationConsent, Integer enabledFlag,
        LocalDateTime createTime, LocalDateTime updateTime) {
        RegisteredClientAggregate aggregate = new RegisteredClientAggregate();
        aggregate.id = id;
        aggregate.clientId = clientId;
        aggregate.clientName = clientName;
        aggregate.clientSecret = clientSecret;
        aggregate.clientAuthenticationMethods = clientAuthenticationMethods;
        aggregate.authorizationGrantTypes = authorizationGrantTypes;
        aggregate.scopes = scopes;
        aggregate.redirectUris = redirectUris;
        aggregate.postLogoutRedirectUris = postLogoutRedirectUris;
        aggregate.requireAuthorizationConsent = requireAuthorizationConsent;
        aggregate.enabledFlag = enabledFlag;
        aggregate.createTime = createTime;
        aggregate.updateTime = updateTime;
        return aggregate;
    }

    public void initRegisterClient(String description, List<String> clientAuthenticationMethods,
        List<String> authorizationGrantTypes, List<String> scopes, List<String> postLogoutRedirectUris,
        List<String> redirectUris, boolean requireAuthorizationConsent, String clientSettings, String tokenSettings) {
        // 验证是否必填项
        Assert.notNull(clientAuthenticationMethods, "clientAuthenticationMethods cannot be null");
        Assert.notNull(authorizationGrantTypes, "authorizationGrantTypes cannot be null");
        Assert.notNull(scopes, "scopes cannot be null");
        Assert.notNull(postLogoutRedirectUris, "postLogoutRedirectUris cannot be null");
        Assert.notNull(redirectUris, "redirectUris cannot be null");
        Assert.notNull(clientSettings, "clientSettings cannot be null");
        Assert.notNull(tokenSettings, "tokenSettings cannot be null");

        this.description = description;
        this.clientAuthenticationMethods = join(clientAuthenticationMethods);
        this.authorizationGrantTypes = join(authorizationGrantTypes);
        this.scopes = join(scopes);
        this.postLogoutRedirectUris = join(postLogoutRedirectUris);
        this.redirectUris = join(redirectUris);
        this.requireAuthorizationConsent = requireAuthorizationConsent;
        this.clientSettings = clientSettings;
        this.tokenSettings = tokenSettings;
    }

    private String join(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        return String.join(",", list);
    }

    public void disable(String reason) {
        this.enabledFlag = 0;
        this.updateTime = LocalDateTime.now();
    }

    public void generateClientIdAndSecret() {
        this.clientId = UUID.randomUUID().toString();
        String secret = UUID.randomUUID().toString();
        this.rawClientSecret = secret;
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        this.clientSecret = "{bcrypt}".concat(encoder.encode(secret));
    }
}
