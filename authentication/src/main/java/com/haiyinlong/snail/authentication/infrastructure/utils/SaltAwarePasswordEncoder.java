package com.haiyinlong.snail.authentication.infrastructure.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SaltAwarePasswordEncoder
 *
 * @author HaiYinLong
 * @version 2025/09/16 18:10
 **/

public class SaltAwarePasswordEncoder implements PasswordEncoder {

    private final String salt;

    public SaltAwarePasswordEncoder(String salt) {
        this.salt = salt;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return PasswordUtil.encryptPassword(rawPassword.toString(), salt);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return PasswordUtil.verifyPassword(rawPassword.toString(), salt, encodedPassword);
    }
}
