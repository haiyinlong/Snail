package com.haiyinlong.snail.authentication.infrastructure.dao.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * OAuth2RegisteredEntity
 *
 * @author HaiYinLong
 * @version 2025/09/15 17:54
 **/
@Data
@TableName("oauth2_registered_client")
public class OAuth2RegisteredClientEntity {
    @TableId(type = IdType.AUTO)
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
    private Integer deletedFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
