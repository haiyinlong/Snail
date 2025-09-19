package com.haiyinlong.snail.authentication.application.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haiyinlong.snail.authentication.application.user.dto.CreateUserRequest;
import com.haiyinlong.snail.authentication.domain.user.UserAggregate;
import com.haiyinlong.snail.authentication.domain.user.repository.UserRepository;
import com.haiyinlong.snail.authentication.domain.user.service.GenerateUsernameDomainService;
import com.haiyinlong.snail.authentication.domain.utils.EmailUtil;
import com.haiyinlong.snail.authentication.infrastructure.utils.PasswordUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * CreateUserService
 *
 * @author HaiYinLong
 * @version 2025/09/16 15:07
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class CreateUserService {
    private final UserRepository userRepository;
    private final GenerateUsernameDomainService generateUsernameDomainService;

    @Transactional(rollbackFor = Exception.class)
    public void createUser(CreateUserRequest createUserRequest) {
        // 验证用户邮箱的合法性
        EmailUtil.validateEmail(createUserRequest.getEmail());
        String username = generateUsernameDomainService.generateUsername(createUserRequest.getUsername(),
            createUserRequest.getEmail());
        String password = PasswordUtil.generatePassword();
        UserAggregate userAggregate = UserAggregate.create(username, password);
        userAggregate.updateProfile(createUserRequest.getRealName(), createUserRequest.getEmail(),
            createUserRequest.getPhone(), createUserRequest.getUserType(), createUserRequest.getDepartmentId(),
            createUserRequest.getDepartmentName(), createUserRequest.getPosition(), createUserRequest.getAuthorities());
        userRepository.saveUser(userAggregate);
        // 发送邮件通知
        // sendEmailNotification(userAggregate, password);
        log.info("用户创建成功: username:{}, password:{}", username, password);
    }

}
