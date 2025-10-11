package com.haiyinlong.snail.authorization.domain.menu.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.haiyinlong.snail.authorization.domain.menu.MenuAggregate;

/**
 * MenuRepository
 *
 * @author HaiYinLong
 * @version 2025/10/10 15:12
 **/
public interface MenuRepository {

    void save(MenuAggregate menu);

    List<MenuAggregate> queryMenuPermissionByMenuIds(@Param("menuIds") List<Long> menuIds);
}
