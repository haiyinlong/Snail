package com.haiyinlong.snail.authentication.infrastructure.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.haiyinlong.snail.authentication.domain.user.UserAggregate;
import com.haiyinlong.snail.authentication.domain.user.repository.UserRepository;
import com.haiyinlong.snail.authentication.infrastructure.utils.SaltAwarePasswordEncoder;

/**
 * 验证用户名密码
 *
 * @author HaiYinLong
 * @version 2025/09/16 18:23
 **/

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    public CustomAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 获取用户信息
        UserAggregate userAggregate = userRepository.getByUsername(username);
        if (userAggregate == null) {
            throw new BadCredentialsException("User not found: " + username);
        }

        // 使用SaltAwarePasswordEncoder验证密码
        SaltAwarePasswordEncoder passwordEncoder = new SaltAwarePasswordEncoder(userAggregate.getSalt());
        if (!passwordEncoder.matches(password, userAggregate.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // 构建认证成功的token
        // AuthorityUtils.commaSeparatedStringToAuthorityList 这里一般放什么？用户角色吗?
        return new UsernamePasswordAuthenticationToken(userAggregate.getId().toString(), password,
            AuthorityUtils.commaSeparatedStringToAuthorityList("USER_ROLE"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
