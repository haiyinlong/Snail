package com.haiyinlong.snail.authorization.infrastructure.repository.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.haiyinlong.snail.authorization.domain.role.RoleAggregate;
import com.haiyinlong.snail.authorization.infrastructure.dao.entity.RoleEntity;

/**
 * RoleToEntityConverter
 *
 * @author HaiYinLong
 * @version 2025/10/10 15:59
 **/
@Mapper
public interface RoleToEntityConverter {
    RoleToEntityConverter INSTANCE = Mappers.getMapper(RoleToEntityConverter.class);

    RoleEntity convert(RoleAggregate role);
}
