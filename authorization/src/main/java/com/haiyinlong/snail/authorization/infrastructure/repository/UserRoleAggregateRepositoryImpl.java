package com.haiyinlong.snail.authorization.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.haiyinlong.snail.authorization.domain.userrole.UserRoleAggregate;
import com.haiyinlong.snail.authorization.domain.userrole.repository.UserRoleAggregateRepository;
import com.haiyinlong.snail.authorization.infrastructure.dao.UserRoleMapper;
import com.haiyinlong.snail.authorization.infrastructure.dao.entity.UserRoleEntity;

import lombok.RequiredArgsConstructor;

/**
 * UserRoleAggregateRepositoryImpl
 *
 * @author HaiYinLong
 * @version 2025/10/10 16:21
 **/
@Repository
@RequiredArgsConstructor
public class UserRoleAggregateRepositoryImpl implements UserRoleAggregateRepository {
    private final UserRoleMapper userRoleMapper;

    @Override
    public void save(UserRoleAggregate userRoleAggregate) {
        List<UserRoleEntity> userRoleEntityList = userRoleAggregate.getRoleIds().stream().map(roleId -> {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userRoleAggregate.getUserId());
            userRoleEntity.setRoleId(roleId);
            return userRoleEntity;
        }).toList();
        userRoleMapper.insertBatch(userRoleEntityList);
    }

    @Override
    public UserRoleAggregate getUserRole(Long userId) {
        List<UserRoleEntity> userRoleEntityList = userRoleMapper.getUserRole(userId);
        return UserRoleAggregate.of(userId, userRoleEntityList.stream().map(UserRoleEntity::getRoleId).toList());
    }
}
