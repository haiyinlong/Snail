package com.haiyinlong.snail.authorization.domain.menu;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * 菜单管理(支持分组、分目录)， 类型 0：分组；1：目录 2：内部菜单 3：外部菜单 4：按钮
 *
 */
@Getter
public class MenuAggregate {
    @Setter
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
    private MenuTypeEnum type;

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

    private MenuAggregate() {}

    private MenuAggregate(Long id, Long parentId, String name, String url, String perms, MenuTypeEnum type, String icon,
        Integer orderNum, String description, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.url = url;
        this.perms = perms;
        this.type = type;
        this.icon = icon;
        this.orderNum = orderNum;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public static MenuAggregate create() {
        return new MenuAggregate();
    }

    public static MenuAggregate of(Long id, Long parentId, String name, String url, String perms, MenuTypeEnum type,
        String icon, Integer orderNum, String description, LocalDateTime createTime, LocalDateTime updateTime) {

        return new MenuAggregate(id, parentId, name, url, perms, type, icon, orderNum, description, createTime,
            updateTime);
    }

    public void createMenu(String name, String url, Long parentId, String icon, Integer type, String perms,
        String description, Integer orderNum) {
        MenuTypeEnum menuType = MenuTypeEnum.getDescByCode(type);
        if (menuType == null) {
            throw new IllegalArgumentException("菜单类型错误");
        }
        switch (menuType) {
            case GROUP:
                setMenuGroup(name, description);
                break;
            case DIRECTORY:
                setMenuDirectory(parentId, name, description, icon, url, perms, orderNum);
                break;
            case INTERNAL_MENU:
                setMenuInner(parentId, name, description, icon, url, perms, orderNum);
                break;
            case EXTERNAL_MENU:
                setMenuOuter(parentId, name, description, icon, url, perms, orderNum);
                break;
            case BUTTON:
                setMenuButton(parentId, name, description, perms);
                break;
            default:
                break;
        }
    }

    public void setMenuGroup(String name, String description) {
        initMenu();
        this.name = name;
        this.description = description;
        this.type = MenuTypeEnum.GROUP;
    }

    public void setMenuDirectory(Long parentId, String name, String description, String icon, String url, String perms,
        Integer orderNum) {
        initMenu();
        this.parentId = parentId;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.url = url;
        this.perms = perms;
        this.type = MenuTypeEnum.DIRECTORY;
        this.orderNum = orderNum;
    }

    public void setMenuInner(Long parentId, String name, String description, String icon, String url, String perms,
        Integer orderNum) {
        initMenu();
        this.parentId = parentId;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.url = url;
        this.perms = perms;
        this.type = MenuTypeEnum.INTERNAL_MENU;
        this.orderNum = orderNum;
    }

    public void setMenuOuter(Long parentId, String name, String description, String icon, String url, String perms,
        Integer orderNum) {
        initMenu();
        this.parentId = parentId;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.url = url;
        this.perms = perms;
        this.type = MenuTypeEnum.EXTERNAL_MENU;
        this.orderNum = orderNum;
    }

    public void setMenuButton(Long parentId, String name, String description, String perms) {
        initMenu();
        this.parentId = parentId;
        this.name = name;
        this.description = description;
        this.perms = perms;
        this.type = MenuTypeEnum.BUTTON;
    }

    private void initMenu() {
        this.parentId = 0L;
        this.orderNum = 0;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

}
