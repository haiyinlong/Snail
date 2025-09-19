package com.haiyinlong.snail.authentication.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haiyinlong.snail.authentication.infrastructure.dao.entity.UserProfileEntity;

/**
 * UserProfileDao
 *
 * @author HaiYinLong
 * @version 2025/09/16 16:40
 **/
@Mapper
public interface UserProfileDao extends BaseMapper<UserProfileEntity> {
    UserProfileEntity getByUserId(@Param("userId") Long userId);
}
