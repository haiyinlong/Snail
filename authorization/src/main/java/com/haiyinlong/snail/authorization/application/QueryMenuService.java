package com.haiyinlong.snail.authorization.application;

import org.springframework.stereotype.Service;

import com.haiyinlong.snail.authorization.application.dto.QueryMenuPageResponse;
import com.haiyinlong.snail.authorization.domain.menu.MenuAggregate;
import com.haiyinlong.snail.authorization.domain.menu.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

/**
 * QueryMenuService
 *
 * @author HaiYinLong
 * @version 2025/10/11 10:53
 **/
@Service
@RequiredArgsConstructor
public class QueryMenuService {
    private final MenuRepository menuRepository;

    public QueryMenuPageResponse queryMenu(Long menuId) {
        MenuAggregate menuAggregate = menuRepository.getMenu(menuId);
        QueryMenuPageResponse queryMenuPageResponse = new QueryMenuPageResponse();
        queryMenuPageResponse.setName(menuAggregate.getName());
        queryMenuPageResponse.setUrl(menuAggregate.getUrl());
        queryMenuPageResponse.setIcon(menuAggregate.getIcon());
        queryMenuPageResponse.setType(menuAggregate.getType().getCode());
        queryMenuPageResponse.setDescription(menuAggregate.getDescription());
        queryMenuPageResponse.setOrderNum(menuAggregate.getOrderNum());
        queryMenuPageResponse.setPerms(menuAggregate.getPerms());
        queryMenuPageResponse.setCreateTime(menuAggregate.getCreateTime());
        queryMenuPageResponse.setUpdateTime(menuAggregate.getUpdateTime());
        queryMenuPageResponse.setId(menuAggregate.getId());
        queryMenuPageResponse.setParentId(menuAggregate.getParentId());
        return queryMenuPageResponse;
    }
}
