package com.haiyinlong.snail.authentication.domain.user.service;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.haiyinlong.snail.authentication.domain.exception.BusinessException;
import com.haiyinlong.snail.authentication.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * 根据用户名和邮箱创建用户名，默认使用自定义用户名，如果没有就使用邮箱
 *
 * @author HaiYinLong
 * @version 2025/09/16 15:57
 **/
@Component
@RequiredArgsConstructor
public class GenerateUsernameDomainService {
    private final UserRepository userRepository;

    public String generateUsername(String username, String email) {
        if (ObjectUtils.isEmpty(username)) {
            // 检查邮件是否已存在
            if (userRepository.existsByUsername(email)) {
                throw new BusinessException("EMAIL_ALREADY_EXISTS", "该邮箱已被注册");
            }
            return email;
        }
        // 检查用户名是否已存在
        validateUsername(username);
        if (userRepository.existsByUsername(username)) {
            throw new BusinessException("USERNAME_EXISTS", "用户名已存在");
        }
        return username;
    }

    public void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new BusinessException("USERNAME_REQUIRED", "用户名不能为空");
        }

        if (username.length() < 3 || username.length() > 50) {
            throw new BusinessException("USERNAME_TOO_LONG", "用户名长度必须在3-50个字符之间");
        }
    }
}
