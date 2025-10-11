package com.haiyinlong.snail.authorization.application.dto;

import java.util.List;

import lombok.Data;

/**
 * CreateUserRoleRequest
 *
 * @author HaiYinLong
 * @version 2025/10/10 16:15
 **/
@Data
public class CreateUserRoleRequest {
    private Long userId;
    private List<Long> roleIds;
}
