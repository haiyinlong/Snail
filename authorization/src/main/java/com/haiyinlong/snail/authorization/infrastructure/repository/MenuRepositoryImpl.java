package com.haiyinlong.snail.authorization.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.haiyinlong.snail.authorization.domain.menu.MenuAggregate;
import com.haiyinlong.snail.authorization.domain.menu.MenuTypeEnum;
import com.haiyinlong.snail.authorization.domain.menu.repository.MenuRepository;
import com.haiyinlong.snail.authorization.infrastructure.dao.MenuMapper;
import com.haiyinlong.snail.authorization.infrastructure.dao.entity.MenuEntity;
import com.haiyinlong.snail.authorization.infrastructure.repository.converter.MenuToEntityConverter;

import lombok.RequiredArgsConstructor;

/**
 * MenuRepositoryImpl
 *
 * @author HaiYinLong
 * @version 2025/10/10 15:12
 **/
@Repository
@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepository {
    private final MenuMapper menuMapper;

    @Override
    public void save(MenuAggregate menu) {
        MenuEntity menuEntity = MenuToEntityConverter.INSTANCE.convert(menu);
        menuMapper.insert(menuEntity);
        menu.setId(menuEntity.getId());
    }

    @Override
    public List<MenuAggregate> queryMenuPermissionByMenuIds(List<Long> menuIds) {
        List<MenuEntity> menuEntityList = menuMapper.queryMenuEntity(menuIds);
        return menuEntityList.stream()
            .map(menuEntity -> MenuAggregate.of(menuEntity.getId(), menuEntity.getParentId(), menuEntity.getName(),
                menuEntity.getUrl(), menuEntity.getPerms(), MenuTypeEnum.getDescByCode(menuEntity.getType()),
                menuEntity.getIcon(), menuEntity.getOrderNum(), menuEntity.getDescription(), menuEntity.getCreateTime(),
                menuEntity.getUpdateTime()))
            .toList();
    }

    @Override
    public List<MenuAggregate> queryAllMenu() {
        List<MenuEntity> menuEntityList = menuMapper.queryAllMenu();
        return menuEntityList.stream()
            .map(menuEntity -> MenuAggregate.of(menuEntity.getId(), menuEntity.getParentId(), menuEntity.getName(),
                menuEntity.getUrl(), menuEntity.getPerms(), MenuTypeEnum.getDescByCode(menuEntity.getType()),
                menuEntity.getIcon(), menuEntity.getOrderNum(), menuEntity.getDescription(), menuEntity.getCreateTime(),
                menuEntity.getUpdateTime()))
            .toList();
    }

    @Override
    public MenuAggregate getMenu(Long menuId) {
        MenuEntity menuEntity = menuMapper.selectById(menuId);
        return MenuAggregate.of(menuEntity.getId(), menuEntity.getParentId(), menuEntity.getName(), menuEntity.getUrl(),
            menuEntity.getPerms(), MenuTypeEnum.getDescByCode(menuEntity.getType()), menuEntity.getIcon(),
            menuEntity.getOrderNum(), menuEntity.getDescription(), menuEntity.getCreateTime(),
            menuEntity.getUpdateTime());
    }
}
