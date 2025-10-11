package com.haiyinlong.snail.authorization.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.haiyinlong.snail.authorization.application.dto.QueryUserMenuAndTeamResponse;
import com.haiyinlong.snail.authorization.application.dto.QueryUserMenuResponse;
import com.haiyinlong.snail.authorization.application.dto.QueryUserTeamResponse;
import com.haiyinlong.snail.authorization.domain.menu.MenuAggregate;
import com.haiyinlong.snail.authorization.domain.menu.repository.MenuRepository;
import com.haiyinlong.snail.authorization.domain.role.RoleAggregate;
import com.haiyinlong.snail.authorization.domain.role.repository.RoleAggregateRepository;
import com.haiyinlong.snail.authorization.domain.userrole.UserRoleAggregate;
import com.haiyinlong.snail.authorization.domain.userrole.repository.UserRoleAggregateRepository;

import lombok.RequiredArgsConstructor;

/**
 * QueryUserMenuService
 *
 * @author HaiYinLong
 * @version 2025/10/10 17:34
 **/
@Service
@RequiredArgsConstructor
public class QueryUserMenuService {
    private final UserRoleAggregateRepository userRoleAggregateRepository;
    private final RoleAggregateRepository roleAggregateRepository;
    private final MenuRepository menuRepository;

    public QueryUserMenuAndTeamResponse queryUserMenu(Long userId) {
        // 先获取用户角色
        UserRoleAggregate userRoleAggregate = userRoleAggregateRepository.getUserRole(userId);
        // 根据角色获取角色权限
        List<RoleAggregate> roleAggregates = roleAggregateRepository.queryRoleByRoleIds(userRoleAggregate.getRoleIds());
        List<Long> permissionIds = getPermissionIds(roleAggregates);
        // 根据权限获取对应的菜单权限
        List<MenuAggregate> menuAggregateList = menuRepository.queryMenuPermissionByMenuIds(permissionIds);
        Map<Long, List<MenuAggregate>> menuParentIdMap = menuAggregateList.stream().sorted(
            (menuAggregate1, menuAggregate2) -> menuAggregate1.getOrderNum().compareTo(menuAggregate2.getOrderNum()))
            .collect(Collectors.groupingBy(MenuAggregate::getParentId));
        List<QueryUserMenuResponse> menuResponses = convertTree(menuParentIdMap.get(0L), menuParentIdMap);
        List<QueryUserTeamResponse> teamResponses =
            List.of(new QueryUserTeamResponse("Acme Inc", "GalleryVerticalEnd", "Enterprise"));
        return new QueryUserMenuAndTeamResponse(teamResponses, menuResponses);
    }

    private List<QueryUserMenuResponse> convertTree(List<MenuAggregate> menuAggregateList,
        Map<Long, List<MenuAggregate>> menuParentIdMap) {
        if (menuAggregateList == null || menuAggregateList.isEmpty()) {
            return null;
        }
        return menuAggregateList.stream().map(menuAggregate -> convertTree(menuAggregate, menuParentIdMap)).toList();
    }

    private QueryUserMenuResponse convertTree(MenuAggregate menuAggregate,
        Map<Long, List<MenuAggregate>> menuParentIdMap) {
        QueryUserMenuResponse queryUserMenuResponse = new QueryUserMenuResponse();
        queryUserMenuResponse.setTitle(menuAggregate.getName());
        queryUserMenuResponse.setUrl(menuAggregate.getUrl());
        queryUserMenuResponse.setIcon(menuAggregate.getIcon());
        queryUserMenuResponse.setType(menuAggregate.getType().getCode());
        queryUserMenuResponse.setItems(
            convertTree(menuParentIdMap.getOrDefault(menuAggregate.getId(), new ArrayList<>()), menuParentIdMap));
        return queryUserMenuResponse;
    }

    private static List<Long> getPermissionIds(List<RoleAggregate> roleAggregates) {
        return roleAggregates.stream().map(RoleAggregate::getPermissions).reduce(new ArrayList<Long>(), (a, b) -> {
            a.addAll(b);
            return a;
        });
    }
}
