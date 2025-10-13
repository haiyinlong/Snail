package com.haiyinlong.snail.authorization.interfaces.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.haiyinlong.snail.authorization.application.CreateMenuService;
import com.haiyinlong.snail.authorization.application.QueryMenuPageService;
import com.haiyinlong.snail.authorization.application.QueryMenuService;
import com.haiyinlong.snail.authorization.application.dto.CreateMenuRequest;
import com.haiyinlong.snail.authorization.application.dto.QueryMenuPageResponse;
import com.haiyinlong.snail.common.dto.ResponseDto;

import lombok.RequiredArgsConstructor;

/**
 * 菜单的增删改查
 *
 * @author HaiYinLong
 * @version 2025/10/11 09:28
 **/
@RequestMapping("/menu")
@RestController
@RequiredArgsConstructor
public class MenuController {
    private final CreateMenuService createMenuService;
    private final QueryMenuPageService queryMenuPageService;
    private final QueryMenuService queryMenuService;

    @PostMapping
    public ResponseDto<Void> createMenu(@RequestBody CreateMenuRequest request) {
        createMenuService.createMenu(request);
        return ResponseDto.success();
    }

    @PutMapping("/{menuId}")
    public ResponseDto<Void> updateMenu(@PathVariable("menuId") Long menuId, CreateMenuRequest request) {
        return ResponseDto.success();
    }

    @DeleteMapping("/{menuId}")
    public ResponseDto<Void> deleteMenu(@PathVariable("menuId") Long menuId) {
        return ResponseDto.success();
    }

    @GetMapping("/{menuId}")
    public ResponseDto<QueryMenuPageResponse> queryMenu(@PathVariable("menuId") Long menuId) {
        QueryMenuPageResponse queryMenuPageResponse = queryMenuService.queryMenu(menuId);
        return ResponseDto.success(queryMenuPageResponse);
    }

    @PostMapping("/all")
    public ResponseDto<List<QueryMenuPageResponse>> queryMenuPage() {
        List<QueryMenuPageResponse> queryMenuPage = queryMenuPageService.queryPage();
        return ResponseDto.success(queryMenuPage);
    }
}
