package com.haiyinlong.snail.authorization.infrastructure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haiyinlong.snail.authorization.infrastructure.dao.entity.MenuEntity;

/**
 * @author user
 * @description 针对表【menu(菜单管理(支持分组、分目录))】的数据库操作Mapper
 * @createDate 2025-10-10 14:24:37
 * @Entity com.haiyinlong.snail.authorization.infrastructure.dao.entity.Menu
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuEntity> {

    List<MenuEntity> queryMenuEntity(@Param("menuIds") List<Long> menuIds);

    List<MenuEntity> queryAllMenu();
}
