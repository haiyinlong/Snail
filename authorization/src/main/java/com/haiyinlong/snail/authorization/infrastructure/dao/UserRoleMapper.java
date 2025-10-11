package com.haiyinlong.snail.authorization.infrastructure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haiyinlong.snail.authorization.infrastructure.dao.entity.UserRoleEntity;

/**
 * @author user
 * @description 针对表【user_role(用户角色)】的数据库操作Mapper
 * @createDate 2025-10-10 14:24:37
 * @Entity com.haiyinlong.snail.authorization.infrastructure.dao.entity.UserRole
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

    void insertBatch(@Param("list") List<UserRoleEntity> userRoleEntityList);

    List<UserRoleEntity> getUserRole(@Param("userId") Long userId);
}
