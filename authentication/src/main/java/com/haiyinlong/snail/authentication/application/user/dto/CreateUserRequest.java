package com.haiyinlong.snail.authentication.application.user.dto;

import lombok.Data;

/**
 * CreateUserRequest
 *
 * @author HaiYinLong
 * @version 2025/09/16 14:58
 **/
@Data
public class CreateUserRequest {
    private String username;
    private String realName;
    private String email;
    private String phone;
    private String position;
    private Integer departmentId;
    private String departmentName;
    private String userType;
    private String authorities;
}
