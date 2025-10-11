package com.haiyinlong.snail.authorization.domain.userrole;

import java.util.List;

import lombok.Getter;

/**
 * 用户角色
 *
 */
@Getter
public class UserRoleAggregate {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private List<Long> roleIds;

    private UserRoleAggregate() {}

    public UserRoleAggregate(Long userId, List<Long> roleIds) {
        this.userId = userId;
        this.roleIds = roleIds;
    }

    public static UserRoleAggregate create() {
        return new UserRoleAggregate();
    }

    public static UserRoleAggregate of(Long userId, List<Long> list) {
        return new UserRoleAggregate(userId, list);
    }

    public void createUserRole(Long userId, List<Long> roleIds) {
        this.userId = userId;
        this.roleIds = roleIds;
    }
}
