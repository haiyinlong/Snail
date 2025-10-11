package com.haiyinlong.snail.authorization.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haiyinlong.snail.authorization.application.dto.CreateRoleRequest;
import com.haiyinlong.snail.authorization.domain.role.RoleAggregate;
import com.haiyinlong.snail.authorization.domain.role.repository.RoleAggregateRepository;

import lombok.RequiredArgsConstructor;

/**
 * CreateRoleService
 *
 * @author HaiYinLong
 * @version 2025/10/10 15:52
 **/
@Service
@RequiredArgsConstructor
public class CreateRoleService {
    private final RoleAggregateRepository roleAggregateRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createRole(CreateRoleRequest createRoleRequest) {
        RoleAggregate role = RoleAggregate.create();
        role.createRole(createRoleRequest.getRoleName(), createRoleRequest.getRoleCode(),
            createRoleRequest.getDescription());
        role.setPermission(createRoleRequest.getMenuIds());
        roleAggregateRepository.save(role);
    }
}
