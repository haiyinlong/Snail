package com.haiyinlong.snail.authentication.domain.user.repository;

import com.haiyinlong.snail.authentication.domain.user.UserAggregate;

/**
 * UserRepository
 *
 * @author HaiYinLong
 * @version 2025/09/16 15:08
 **/
public interface UserRepository {

    void saveUser(UserAggregate userAggregate);

    boolean existsByUsername(String username);

    UserAggregate getByUsername(String username);

    UserAggregate getById(Long userId);

    void updatePassword(UserAggregate userAggregate);
}
