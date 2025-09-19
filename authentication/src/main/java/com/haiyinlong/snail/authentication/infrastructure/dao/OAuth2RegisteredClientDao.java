package com.haiyinlong.snail.authentication.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haiyinlong.snail.authentication.infrastructure.dao.entity.OAuth2RegisteredClientEntity;

/**
 * OAuth2RegisteredClientEntityDao
 *
 * @author HaiYinLong
 * @version 2025/09/15 18:18
 **/
@Mapper
public interface OAuth2RegisteredClientDao extends BaseMapper<OAuth2RegisteredClientEntity> {

    OAuth2RegisteredClientEntity selectByClientId(@Param("clientId") String clientId);
}
