package com.haiyinlong.snail.authorization.domain.userrole.repository;

import com.haiyinlong.snail.authorization.domain.userrole.UserRoleAggregate;

/**
 * UserRoleAggregateRepository
 *
 * @author HaiYinLong
 * @version 2025/10/10 16:21
 **/
public interface UserRoleAggregateRepository {
    void save(UserRoleAggregate userRoleAggregate);

    UserRoleAggregate getUserRole(Long userId);
}
