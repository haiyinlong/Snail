-- auth.oauth2_authorization definition

CREATE TABLE `oauth2_authorization`
(
    `id`                            varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
    `registered_client_id`          varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
    `principal_name`                varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
    `authorization_grant_type`      varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
    `authorized_scopes`             varchar(1000) COLLATE utf8mb4_unicode_ci     DEFAULT NULL,
    `attributes`                    blob,
    `state`                         varchar(500) COLLATE utf8mb4_unicode_ci      DEFAULT NULL,
    `authorization_code_value`      blob,
    `authorization_code_issued_at`  timestamp                               NULL DEFAULT NULL,
    `authorization_code_expires_at` timestamp                               NULL DEFAULT NULL,
    `authorization_code_metadata`   blob,
    `access_token_value`            blob,
    `access_token_issued_at`        timestamp                               NULL DEFAULT NULL,
    `access_token_expires_at`       timestamp                               NULL DEFAULT NULL,
    `access_token_metadata`         blob,
    `access_token_type`             varchar(100) COLLATE utf8mb4_unicode_ci      DEFAULT NULL,
    `access_token_scopes`           varchar(1000) COLLATE utf8mb4_unicode_ci     DEFAULT NULL,
    `oidc_id_token_value`           blob,
    `oidc_id_token_issued_at`       timestamp                               NULL DEFAULT NULL,
    `oidc_id_token_expires_at`      timestamp                               NULL DEFAULT NULL,
    `oidc_id_token_metadata`        blob,
    `refresh_token_value`           blob,
    `refresh_token_issued_at`       timestamp                               NULL DEFAULT NULL,
    `refresh_token_expires_at`      timestamp                               NULL DEFAULT NULL,
    `refresh_token_metadata`        blob,
    `user_code_value`               blob,
    `user_code_issued_at`           timestamp                               NULL DEFAULT NULL,
    `user_code_expires_at`          timestamp                               NULL DEFAULT NULL,
    `user_code_metadata`            blob,
    `device_code_value`             blob,
    `device_code_issued_at`         timestamp                               NULL DEFAULT NULL,
    `device_code_expires_at`        timestamp                               NULL DEFAULT NULL,
    `device_code_metadata`          blob,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


-- auth.oauth2_authorization_consent definition

CREATE TABLE `oauth2_authorization_consent`
(
    `registered_client_id` varchar(100) COLLATE utf8mb4_unicode_ci  NOT NULL,
    `principal_name`       varchar(200) COLLATE utf8mb4_unicode_ci  NOT NULL,
    `authorities`          varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`registered_client_id`, `principal_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


-- auth.oauth2_registered_client definition

CREATE TABLE `oauth2_registered_client`
(
    `id`                            int                                      NOT NULL AUTO_INCREMENT,
    `client_id`                     varchar(100) COLLATE utf8mb4_unicode_ci  NOT NULL,
    `client_id_issued_at`           timestamp                                NOT NULL             DEFAULT CURRENT_TIMESTAMP,
    `raw_client_secret`             varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `client_secret`                 varchar(200) COLLATE utf8mb4_unicode_ci                       DEFAULT NULL,
    `client_secret_expires_at`      timestamp                                NULL                 DEFAULT NULL,
    `client_name`                   varchar(200) COLLATE utf8mb4_unicode_ci  NOT NULL,
    `client_authentication_methods` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
    `authorization_grant_types`     varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
    `redirect_uris`                 varchar(1000) COLLATE utf8mb4_unicode_ci                      DEFAULT NULL,
    `post_logout_redirect_uris`     varchar(1000) COLLATE utf8mb4_unicode_ci                      DEFAULT NULL,
    `scopes`                        varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
    `require_authorization_consent` tinyint                                  NOT NULL,
    `description`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `enabled_flag`                  tinyint unsigned                                              DEFAULT NULL COMMENT '1 表示启用;0 表示禁用',
    `deleted_flag`                  tinyint unsigned                                              DEFAULT NULL COMMENT '1 表示删除， 0 表示未删除。',
    `create_time`                   datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `update_time`                   datetime                                                      DEFAULT NULL COMMENT '更新时间',
    `client_settings`               varchar(2000) COLLATE utf8mb4_unicode_ci                      DEFAULT NULL,
    `token_settings`                varchar(2000) COLLATE utf8mb4_unicode_ci                      DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `oauth2_registered_client_unique` (`client_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


-- auth.`user` definition

CREATE TABLE `user`
(
    `id`                      bigint                                  NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`                varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户名',
    `password`                varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `salt`                    varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码盐值',
    `enabled`                 tinyint                                DEFAULT '1' COMMENT '账户启用状态：1-启用，0-禁用',
    `account_non_expired`     tinyint                                DEFAULT '1' COMMENT '账户是否未过期：1-未过期，0-已过期',
    `account_non_locked`      tinyint                                DEFAULT '1' COMMENT '账户是否未锁定：1-未锁定，0-已锁定',
    `credentials_non_expired` tinyint                                DEFAULT '1' COMMENT '凭证是否未过期：1-未过期，0-已过期',
    `create_time`             datetime                               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`             datetime                               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户认证表';


-- auth.user_profile definition

CREATE TABLE `user_profile`
(
    `id`              bigint                                 NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id`         bigint                                 NOT NULL COMMENT '用户ID',
    `user_type`       varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL               DEFAULT 'INTERNAL' COMMENT '用户类型：INTERNAL-内部用户，PARTNER-合作方，EXTERNAL-外聘人员',
    `real_name`       varchar(100) COLLATE utf8mb4_unicode_ci                       DEFAULT NULL COMMENT '真实名称',
    `email`           varchar(100) COLLATE utf8mb4_unicode_ci                       DEFAULT NULL COMMENT '邮件',
    `phone`           varchar(20) COLLATE utf8mb4_unicode_ci                        DEFAULT NULL COMMENT '手机号',
    `department_id`   bigint                                                        DEFAULT NULL COMMENT '部门ID',
    `department_name` varchar(100) COLLATE utf8mb4_unicode_ci                       DEFAULT NULL COMMENT '部门名称',
    `position`        varchar(100) COLLATE utf8mb4_unicode_ci                       DEFAULT NULL COMMENT '职务',
    `avatar_url`      varchar(255) COLLATE utf8mb4_unicode_ci                       DEFAULT NULL COMMENT '头像url',
    `authorities`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限',
    `create_time`     datetime                                                      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime                                                      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `user_profiles_user_id_IDX` (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户信息';
