package com.haiyinlong.snail.authentication.infrastructure.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.haiyinlong.snail.authentication.domain.user.UserAggregate;
import com.haiyinlong.snail.authentication.infrastructure.dao.entity.UserEntity;
import com.haiyinlong.snail.authentication.infrastructure.dao.entity.UserProfileEntity;

/**
 * UserAggregateToEntityConverter
 *
 * @author HaiYinLong
 * @version 2025/09/16 15:11
 **/
@Mapper
public interface UserAggregateToEntityConverter {
    UserAggregateToEntityConverter INSTANCE = Mappers.getMapper(UserAggregateToEntityConverter.class);

    UserEntity convert(UserAggregate userAggregate);

    UserProfileEntity convertProfile(UserAggregate userAggregate);

}
