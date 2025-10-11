package com.haiyinlong.snail.authorization.domain.menu;

import lombok.Getter;

/**
 * MenuTypeEnum
 *
 * @author HaiYinLong
 * @version 2025/10/10 14:59
 **/
@Getter
public enum MenuTypeEnum {
    GROUP(0, "分组"), DIRECTORY(1, "目录"), INTERNAL_MENU(2, "内部菜单"), EXTERNAL_MENU(3, "外部菜单"), BUTTON(4, "按钮");

    private final Integer code;
    private final String desc;

    MenuTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static MenuTypeEnum getDescByCode(Integer code) {
        for (MenuTypeEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
