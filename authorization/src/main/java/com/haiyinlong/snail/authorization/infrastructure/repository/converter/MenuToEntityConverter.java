package com.haiyinlong.snail.authorization.infrastructure.repository.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.haiyinlong.snail.authorization.domain.menu.MenuAggregate;
import com.haiyinlong.snail.authorization.domain.menu.MenuTypeEnum;
import com.haiyinlong.snail.authorization.infrastructure.dao.entity.MenuEntity;

/**
 * MenuToEntityConverter
 *
 * @author HaiYinLong
 * @version 2025/10/10 15:22
 **/
@Mapper(imports = MenuTypeEnum.class)
public interface MenuToEntityConverter {
    MenuToEntityConverter INSTANCE = Mappers.getMapper(MenuToEntityConverter.class);

    @Mapping(target = "type", source = "type", qualifiedByName = "MenuTypeEnum.getCode()")
    MenuEntity convert(MenuAggregate menu);

    @Named("MenuTypeEnum.getCode()")
    default Integer getMenuTypeCode(MenuTypeEnum menuTypeEnum) {
        if (menuTypeEnum == null) {
            return null;
        }
        return menuTypeEnum.getCode();
    }
}
