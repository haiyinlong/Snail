package com.haiyinlong.snail.authentication.domain.client.repository;

import com.haiyinlong.snail.authentication.domain.client.RegisteredClientAggregate;

/**
 * RegisteredClientRepository
 *
 * @author HaiYinLong
 * @version 2025/09/16 12:08
 **/
public interface RegisteredClientRepository {
    void save(RegisteredClientAggregate registeredClientAggregate);

}
