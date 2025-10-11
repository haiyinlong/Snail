package com.haiyinlong.snail.authorization.application.dto;

import java.util.List;

import lombok.Data;

/**
 * CreateRoleRequest
 *
 * @author HaiYinLong
 * @version 2025/10/10 15:53
 **/
@Data
public class CreateRoleRequest {
    private String roleName;
    private String roleCode;
    private String description;
    private List<Long> menuIds;
}
