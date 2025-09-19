package com.haiyinlong.snail.authentication.application.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haiyinlong.snail.authentication.application.user.dto.UpdatePasswordRequest;
import com.haiyinlong.snail.authentication.domain.user.UserAggregate;
import com.haiyinlong.snail.authentication.domain.user.repository.UserRepository;
import com.haiyinlong.snail.common.util.RequestUtil;

import lombok.RequiredArgsConstructor;

/**
 * ChangePasswordService
 *
 * @author HaiYinLong
 * @version 2025/09/19 11:11
 **/
@Service
@RequiredArgsConstructor
public class ChangePasswordService {
    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        // 验证密码
        updatePasswordRequest.verifyPassword();
        // 获取用户聚合根
        Long userId = RequestUtil.getUserId();
        UserAggregate userAggregate = userRepository.getById(userId);
        // 更新密码
        userAggregate.updatePassword(updatePasswordRequest.getNewPassword());
        // 更新密码
        userRepository.updatePassword(userAggregate);
    }
}
