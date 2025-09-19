package com.haiyinlong.snail.authentication.domain.utils;

import com.haiyinlong.snail.authentication.domain.exception.BusinessException;

/**
 * EmailUtil
 *
 * @author HaiYinLong
 * @version 2025/09/16 16:07
 **/
public class EmailUtil {
    public static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new BusinessException("EMAIL_REQUIRED", "邮箱地址不能为空");
        }

        // 邮箱格式验证
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)) {
            throw new BusinessException("INVALID_EMAIL_FORMAT", "邮箱格式不正确: " + email);
        }

        // 邮箱长度验证
        if (email.length() > 255) {
            throw new BusinessException("EMAIL_TOO_LONG", "邮箱地址长度不能超过255个字符");
        }
    }
}
