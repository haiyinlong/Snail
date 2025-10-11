package com.haiyinlong.snail.authorization.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.haiyinlong.snail.authorization.domain.role.RoleAggregate;
import com.haiyinlong.snail.authorization.domain.role.repository.RoleAggregateRepository;
import com.haiyinlong.snail.authorization.infrastructure.dao.RoleMapper;
import com.haiyinlong.snail.authorization.infrastructure.dao.RolePermissionMapper;
import com.haiyinlong.snail.authorization.infrastructure.dao.entity.RoleEntity;
import com.haiyinlong.snail.authorization.infrastructure.dao.entity.RolePermissionEntity;
import com.haiyinlong.snail.authorization.infrastructure.repository.converter.RoleToEntityConverter;

import lombok.RequiredArgsConstructor;

/**
 * RoleAggregateRepositoryImpl
 *
 * @author HaiYinLong
 * @version 2025/10/10 15:56
 **/
@Repository
@RequiredArgsConstructor
public class RoleAggregateRepositoryImpl implements RoleAggregateRepository {
    private final RoleMapper roleMapper;
    private final RolePermissionMapper rolePermissionMapper;

    @Override
    public void save(RoleAggregate role) {
        RoleEntity roleEntity = RoleToEntityConverter.INSTANCE.convert(role);
        roleMapper.insert(roleEntity);
        List<RolePermissionEntity> rolePermissionEntityList = role.getPermissions().stream().map(permissionId -> {
            RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
            rolePermissionEntity.setRoleId(roleEntity.getId());
            rolePermissionEntity.setMenuId(permissionId);
            return rolePermissionEntity;
        }).collect(Collectors.toList());
        rolePermissionMapper.insertBatch(rolePermissionEntityList);
    }

    @Override
    public List<RoleAggregate> queryRoleByRoleIds(List<Long> roleIds) {
        List<RoleEntity> roleEntityList = roleMapper.queryRoleByRoleIds(roleIds);
        List<RolePermissionEntity> rolePermissionEntities =
            rolePermissionMapper.queryPermissionEntityByRoleIds(roleIds);
        Map<Long, List<Long>> rolePermissionMap =
            rolePermissionEntities.stream().collect(Collectors.groupingBy(RolePermissionEntity::getRoleId,
                Collectors.mapping(RolePermissionEntity::getMenuId, Collectors.toList())));

        return roleEntityList.stream()
            .map(roleEntity -> RoleAggregate.of(roleEntity.getId(), roleEntity.getRoleName(), roleEntity.getRoleCode(),
                roleEntity.getDescription(), roleEntity.getCreateTime(), roleEntity.getUpdateTime(),
                rolePermissionMap.getOrDefault(roleEntity.getId(), new ArrayList<>())))
            .toList();
    }

}
