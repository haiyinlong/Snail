package com.haiyinlong.snail.authorization.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haiyinlong.snail.authorization.application.dto.CreateUserRoleRequest;
import com.haiyinlong.snail.authorization.domain.userrole.UserRoleAggregate;
import com.haiyinlong.snail.authorization.domain.userrole.repository.UserRoleAggregateRepository;

import lombok.RequiredArgsConstructor;

/**
 * CreateUserRoleService
 *
 * @author HaiYinLong
 * @version 2025/10/10 16:14
 **/
@Service
@RequiredArgsConstructor
public class CreateUserRoleService {

    private final UserRoleAggregateRepository userRoleAggregateRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createUserRole(CreateUserRoleRequest createUserRoleRequest) {
        UserRoleAggregate userRoleAggregate = UserRoleAggregate.create();
        userRoleAggregate.createUserRole(createUserRoleRequest.getUserId(), createUserRoleRequest.getRoleIds());
        userRoleAggregateRepository.save(userRoleAggregate);
    }
}
