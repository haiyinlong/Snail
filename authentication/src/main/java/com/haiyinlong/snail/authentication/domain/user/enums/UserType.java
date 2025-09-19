package com.haiyinlong.snail.authentication.domain.user.enums;
/**
 * UserType
 *
 * @author HaiYinLong
 * @version 2025/09/16 16:36
 **/

/**
 * 用户类型枚举
 */
public enum UserType {
    /**
     * 内部用户 - 公司正式员工
     */
    INTERNAL("INTERNAL", "内部用户"),

    /**
     * 合作方 - 合作伙伴、供应商等
     */
    PARTNER("PARTNER", "合作方"),

    /**
     * 外聘人员 - 临时工、外包人员等
     */
    EXTERNAL("EXTERNAL", "外聘人员");

    private final String code;
    private final String description;

    UserType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static UserType fromCode(String code) {
        for (UserType type : UserType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未知的用户类型: " + code);
    }
}
