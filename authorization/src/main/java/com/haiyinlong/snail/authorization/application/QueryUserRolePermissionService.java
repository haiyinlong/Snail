package com.haiyinlong.snail.authorization.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.haiyinlong.snail.authorization.application.dto.QueryUserRolePermissionResponse;
import com.haiyinlong.snail.authorization.domain.menu.MenuAggregate;
import com.haiyinlong.snail.authorization.domain.menu.repository.MenuRepository;
import com.haiyinlong.snail.authorization.domain.role.RoleAggregate;
import com.haiyinlong.snail.authorization.domain.role.repository.RoleAggregateRepository;
import com.haiyinlong.snail.authorization.domain.userrole.UserRoleAggregate;
import com.haiyinlong.snail.authorization.domain.userrole.repository.UserRoleAggregateRepository;

import lombok.RequiredArgsConstructor;

/**
 * QueryUserRolePermissionService
 *
 * @author HaiYinLong
 * @version 2025/10/10 16:34
 **/
@Service
@RequiredArgsConstructor
public class QueryUserRolePermissionService {
    private final UserRoleAggregateRepository userRoleAggregateRepository;
    private final RoleAggregateRepository roleAggregateRepository;
    private final MenuRepository menuRepository;

    public QueryUserRolePermissionResponse queryUserRolePermission(Long userId) {
        // 先获取用户角色
        UserRoleAggregate userRoleAggregate = userRoleAggregateRepository.getUserRole(userId);
        // 根据角色获取角色权限
        List<RoleAggregate> roleAggregates = roleAggregateRepository.queryRoleByRoleIds(userRoleAggregate.getRoleIds());
        List<Long> permissionIds = getPermissionIds(roleAggregates);
        List<String> roleCodes = roleAggregates.stream().map(RoleAggregate::getRoleCode).toList();
        // 根据权限获取对应的菜单权限
        List<MenuAggregate> menuAggregateList = menuRepository.queryMenuPermissionByMenuIds(permissionIds);
        List<String> menuPermissions = menuAggregateList.stream().map(MenuAggregate::getPerms).toList();
        QueryUserRolePermissionResponse queryUserRolePermissionResponse = new QueryUserRolePermissionResponse();
        queryUserRolePermissionResponse.setUserId(userId);
        queryUserRolePermissionResponse.setPermissions(menuPermissions);
        queryUserRolePermissionResponse.setRoles(roleCodes);
        return queryUserRolePermissionResponse;
    }

    private static List<Long> getPermissionIds(List<RoleAggregate> roleAggregates) {
        return roleAggregates.stream().map(RoleAggregate::getPermissions).reduce(new ArrayList<Long>(), (a, b) -> {
            a.addAll(b);
            return a;
        });
    }
}
