package com.haiyinlong.snail.authentication.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haiyinlong.snail.authentication.infrastructure.dao.entity.UserEntity;

/**
 * UserDao
 *
 * @author HaiYinLong
 * @version 2025/09/16 15:10
 **/
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    boolean existsByUsername(@Param("username") String username);

    UserEntity getByUsername(@Param("username") String username);
}
