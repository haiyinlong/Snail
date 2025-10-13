package com.haiyinlong.snail.authorization.application.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * QueryMenuPageResponse
 *
 * @author HaiYinLong
 * @version 2025/10/11 10:40
 **/
@Data
public class QueryMenuPageResponse {
    private Long id;

    /**
     * 父菜单ID，一级菜单为0
     */
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
     * 排序
     */
    private Integer orderNum;

    /**
     * 菜单描述，中文
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private List<QueryMenuPageResponse> children;

}
