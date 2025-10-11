package com.haiyinlong.snail.authorization.application.dto;

import lombok.Data;

/**
 * CreateMenuRequest
 *
 * @author HaiYinLong
 * @version 2025/10/10 15:08
 **/
@Data
public class CreateMenuRequest {
    private Long parentId;

    /**
     * 菜单名称(可以是前端国际化配置)
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型 0：分组；1：目录 2：内部菜单 3：外部菜单 4：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单描述，中文
     */
    private String description;
    /**
     * 排序
     */
    private Integer orderNum;
}
