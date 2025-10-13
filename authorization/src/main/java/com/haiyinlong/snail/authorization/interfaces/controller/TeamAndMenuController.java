package com.haiyinlong.snail.authorization.interfaces.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haiyinlong.snail.authorization.application.QueryUserMenuService;
import com.haiyinlong.snail.authorization.application.dto.QueryUserMenuAndTeamResponse;
import com.haiyinlong.snail.common.dto.ResponseDto;

import lombok.RequiredArgsConstructor;

/**
 * TeamAndMenuController
 *
 * @author HaiYinLong
 * @version 2025/10/11 09:28
 **/
@RequestMapping("/teamMenu")
@RestController
@RequiredArgsConstructor
public class TeamAndMenuController {
    private final QueryUserMenuService queryUserMenuService;

    @GetMapping
    public ResponseDto<QueryUserMenuAndTeamResponse> queryUserMenu(@RequestHeader("user-id") Long userId) {

        return ResponseDto.success(queryUserMenuService.queryUserMenu(userId));
    }
}
