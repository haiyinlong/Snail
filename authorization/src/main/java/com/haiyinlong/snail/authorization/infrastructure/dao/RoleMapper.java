package com.haiyinlong.snail.authorization.infrastructure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haiyinlong.snail.authorization.infrastructure.dao.entity.RoleEntity;

/**
 * @author user
 * @description 针对表【role(角色)】的数据库操作Mapper
 * @createDate 2025-10-10 14:24:37
 * @Entity com.haiyinlong.snail.authorization.infrastructure.dao.entity.Role
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {

    List<RoleEntity> queryRoleByRoleIds(@Param("roleIds") List<Long> roleIds);
}
