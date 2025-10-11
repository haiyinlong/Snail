package com.haiyinlong.snail.authorization.domain.role;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

/**
 * 角色
 *
 * @TableName role
 */
@Getter
public class RoleAggregate {
    /**
     *
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 备注
     */
    private String description;

    /**
     * 权限
     */
    private List<Long> permissions;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private RoleAggregate() {}

    private RoleAggregate(Long id, String roleName, String roleCode, String description, LocalDateTime createTime,
        LocalDateTime updateTime, List<Long> permissions) {
        this.id = id;
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.description = description;
        this.permissions = permissions;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public static RoleAggregate create() {
        return new RoleAggregate();
    }

    public static RoleAggregate of(Long id, String roleName, String roleCode, String description,
        LocalDateTime createTime, LocalDateTime updateTime, List<Long> menuIds) {
        return new RoleAggregate(id, roleName, roleCode, description, createTime, updateTime, menuIds);
    }

    public void createRole(String roleName, String roleCode, String description) {
        initRole();
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.description = description;
    }

    private void initRole() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    public void setPermission(List<Long> menuIds) {
        this.permissions = menuIds;
        this.updateTime = LocalDateTime.now();
    }
}
