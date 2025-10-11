package com.haiyinlong.snail.authorization.infrastructure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haiyinlong.snail.authorization.infrastructure.dao.entity.RolePermissionEntity;

/**
 * @author user
 * @description 针对表【role_menu(角色菜单)】的数据库操作Mapper
 * @createDate 2025-10-10 14:24:37
 * @Entity com.haiyinlong.snail.authorization.infrastructure.dao.entity.RoleMenu
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermissionEntity> {

    void insertBatch(@Param("list") List<RolePermissionEntity> rolePermissionEntityList);

    List<RolePermissionEntity> queryPermissionEntityByRoleIds(@Param("roleIds") List<Long> roleIds);
}
