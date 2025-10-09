package com.haiyinlong.snail.authentication.infrastructure.security;

import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.stereotype.Component;

import com.haiyinlong.snail.authentication.domain.user.UserAggregate;
import com.haiyinlong.snail.authentication.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * CustomUserInfoCustomizer
 *
 * @author HaiYinLong
 * @version 2025/09/18 09:53
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomOidcUserInfoMapper {
    private final UserRepository userRepository;

    public OidcUserInfo apply(OidcUserInfoAuthenticationContext context) {
        OidcUserInfoAuthenticationToken authentication = context.getAuthentication();
        String userId = authentication.getName();

        // 根据用户名获取用户信息
        UserAggregate user = userRepository.getById(Long.parseLong(userId));

        if (user != null) {
            // TODO 返回权限permissions
            return OidcUserInfo.builder().subject(userId).name(user.getRealName()).email(user.getEmail())
                .claim("userId", user.getId().toString()).claim("realName", user.getRealName())
                .claim("deptId", user.getDepartmentId()).claim("dept", user.getDepartmentName()).build();
        } else {
            // 如果找不到用户，返回基本的subject信息
            return new OidcUserInfo(java.util.Map.of("sub", userId));
        }
    }
}
