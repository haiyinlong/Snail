package com.haiyinlong.snail.authorization.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haiyinlong.snail.authorization.application.dto.CreateMenuRequest;
import com.haiyinlong.snail.authorization.domain.menu.MenuAggregate;
import com.haiyinlong.snail.authorization.domain.menu.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

/**
 * CreateMenuService
 *
 * @author HaiYinLong
 * @version 2025/10/10 15:08
 **/
@Service
@RequiredArgsConstructor
public class CreateMenuService {
    private final MenuRepository menuRepository;

    @Transactional(rollbackFor = Exception.class)
    public Long createMenu(CreateMenuRequest request) {
        MenuAggregate menu = MenuAggregate.create();
        menu.createMenu(request.getName(), request.getUrl(), request.getParentId(), request.getIcon(),
            request.getType(), request.getPerms(), request.getDescription(), request.getOrderNum());
        menuRepository.save(menu);
        return menu.getId();
    }
}
