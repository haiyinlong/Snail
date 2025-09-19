package com.haiyinlong.snail.authentication.interfaces.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haiyinlong.snail.authentication.application.user.ChangePasswordService;
import com.haiyinlong.snail.authentication.application.user.CreateUserService;
import com.haiyinlong.snail.authentication.application.user.dto.CreateUserRequest;
import com.haiyinlong.snail.authentication.application.user.dto.UpdatePasswordRequest;
import com.haiyinlong.snail.authentication.infrastructure.dto.ResponseDto;

import lombok.RequiredArgsConstructor;

/**
 * UserController
 *
 * @author HaiYinLong
 * @version 2025/09/16 17:02
 **/
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final CreateUserService createUserService;
    private final ChangePasswordService changePasswordService;

    @PostMapping("")
    public ResponseDto<Void> createUser(@RequestBody CreateUserRequest createUserRequest) throws Exception {
        createUserService.createUser(createUserRequest);
        return ResponseDto.success();
    }

    @PostMapping("/changePassword")
    public ResponseDto<Void> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) throws Exception {
        changePasswordService.updatePassword(updatePasswordRequest);
        return ResponseDto.success();
    }
}
