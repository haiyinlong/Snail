package com.haiyinlong.snail.authentication.infrastructure.security;

import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import com.haiyinlong.snail.authentication.domain.user.UserAggregate;
import com.haiyinlong.snail.authentication.domain.user.repository.UserRepository;

/**
 * 自定义jwt返回值
 *
 * @author HaiYinLong
 * @version 2025/09/17 16:06
 **/

@Component
public class CustomJwtTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {
    private final UserRepository userRepository;

    public CustomJwtTokenCustomizer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void customize(JwtEncodingContext context) {
        if (context.getPrincipal().getPrincipal() instanceof String userId) {
            UserAggregate user = userRepository.getById(Long.parseLong(userId));
            if (user != null) {
                context.getClaims().claim("userId", user.getId().toString()).claim("realName", user.getRealName())
                    .claim("deptId", user.getDepartmentId().toString()).claim("dept", user.getDepartmentName())
                    .claim("roles", user.getAuthorities()).claim("email", user.getEmail());
            }
        }
    }
}
