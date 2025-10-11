package com.haiyinlong.snail.authorization.application.dto;

import java.util.List;

import lombok.Data;

/**
 * QueryUserRolePermissionResponse
 *
 * @author HaiYinLong
 * @version 2025/10/10 16:34
 **/
@Data
public class QueryUserRolePermissionResponse {
    private Long userId;
    private List<String> roles;
    private List<String> permissions;
}
