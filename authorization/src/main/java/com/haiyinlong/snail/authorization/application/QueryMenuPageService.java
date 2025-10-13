package com.haiyinlong.snail.authorization.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.haiyinlong.snail.authorization.application.dto.QueryMenuPageResponse;
import com.haiyinlong.snail.authorization.domain.menu.MenuAggregate;
import com.haiyinlong.snail.authorization.domain.menu.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

/**
 * QueryMenuPageService
 *
 * @author HaiYinLong
 * @version 2025/10/11 10:53
 **/
@Service
@RequiredArgsConstructor
public class QueryMenuPageService {
    private final MenuRepository menuRepository;

    public List<QueryMenuPageResponse> queryPage() {
        List<MenuAggregate> menuAggregateList = menuRepository.queryAllMenu();
        // 进行数据转树形
        Map<Long, List<MenuAggregate>> parentMenuMap =
            menuAggregateList.stream().collect(Collectors.groupingBy(MenuAggregate::getParentId));
        return convertTree(parentMenuMap.get(0L), parentMenuMap);
    }

    private List<QueryMenuPageResponse> convertTree(List<MenuAggregate> menuAggregateList,
        Map<Long, List<MenuAggregate>> parentMenuMap) {
        if (menuAggregateList == null || menuAggregateList.isEmpty()) {
            return null;
        }
        return menuAggregateList.stream().map(menuAggregate -> convertTree(menuAggregate, parentMenuMap)).toList();
    }

    private QueryMenuPageResponse convertTree(MenuAggregate menuAggregate,
        Map<Long, List<MenuAggregate>> parentMenuMap) {
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
        queryMenuPageResponse.setChildren(
            convertTree(parentMenuMap.getOrDefault(menuAggregate.getId(), new ArrayList<>()), parentMenuMap));
        return queryMenuPageResponse;
    }
}
