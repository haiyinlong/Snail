package com.haiyinlong.snail.authorization.domain.role.repository;

import java.util.List;

import com.haiyinlong.snail.authorization.domain.role.RoleAggregate;

/**
 * RoleAggregateRepository
 *
 * @author HaiYinLong
 * @version 2025/10/10 15:50
 **/
public interface RoleAggregateRepository {

    void save(RoleAggregate role);

    List<RoleAggregate> queryRoleByRoleIds(List<Long> roleIds);
}
