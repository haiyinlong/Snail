package com.haiyinlong.snail.authentication.application.user.dto;

import com.haiyinlong.snail.authentication.domain.exception.BusinessException;

import lombok.Data;

/**
 * UpdatePasswordRequest
 *
 * @author HaiYinLong
 * @version 2025/09/19 11:07
 **/
@Data
public class UpdatePasswordRequest {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public void verifyPassword() {
        if (!newPassword.equals(confirmPassword)) {
            throw new BusinessException("PASSWORD_INCONSISTENT", "新密码和确认密码不一致");
        }
        if (newPassword.equals(oldPassword)) {
            throw new BusinessException("NEW_PASSWORD_SAME_OLD_PASSWORD", "新密码不能和旧密码一致");
        }
        if (newPassword.length() < 6 || newPassword.length() > 20) {
            throw new BusinessException("PASSWORD_LENGTH_ERROR", "密码长度必须在6-20位之间");
        }
    }
}
