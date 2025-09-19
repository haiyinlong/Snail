package com.haiyinlong.snail.authentication.domain.user;

import java.time.LocalDateTime;

import com.haiyinlong.snail.authentication.infrastructure.utils.PasswordUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * UserAggregate
 *
 * @author HaiYinLong
 * @version 2025/09/16 15:00
 **/
@Getter
public class UserAggregate {
    @Setter
    private Long id;
    private String username;
    private String password;
    private String salt;
    private Integer enabled;
    private Integer accountNonExpired;
    private Integer accountNonLocked;
    private Integer credentialsNonExpired;

    private String authorities;
    private String userType;
    private String realName;
    private String email;
    private String phone;
    private Integer departmentId;
    private String departmentName;
    private String position;
    private String avatar;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /**
     * 创建用户
     *
     * @param username 用户名
     * @param password 密码(明文)
     * @return 用户聚合
     */
    public static UserAggregate create(String username, String password) {
        UserAggregate userAggregate = new UserAggregate();
        userAggregate.username = username;
        userAggregate.salt = PasswordUtil.generateSalt();
        userAggregate.password = PasswordUtil.encryptPassword(password, userAggregate.salt);
        userAggregate.enabled = 1;
        userAggregate.accountNonExpired = 1;
        userAggregate.accountNonLocked = 1;
        userAggregate.credentialsNonExpired = 1;
        userAggregate.createTime = LocalDateTime.now();
        userAggregate.updateTime = LocalDateTime.now();
        return userAggregate;
    }

    public static UserAggregate of(Long id, String username, String password, String salt, Integer accountNonExpired,
        Integer accountNonLocked, Integer credentialsNonExpired, Integer enabled, LocalDateTime createTime,
        LocalDateTime updateTime) {
        UserAggregate userAggregate = new UserAggregate();
        userAggregate.id = id;
        userAggregate.username = username;
        userAggregate.password = password;
        userAggregate.salt = salt;
        userAggregate.accountNonExpired = accountNonExpired;
        userAggregate.accountNonLocked = accountNonLocked;
        userAggregate.credentialsNonExpired = credentialsNonExpired;
        userAggregate.enabled = enabled;
        userAggregate.createTime = createTime;
        userAggregate.updateTime = updateTime;
        return userAggregate;
    }

    public void updateProfile(String realName, String email, String phone, String userType, Integer departmentId,
        String departmentName, String position, String authorities) {
        this.realName = realName;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.position = position;
        this.authorities = authorities;
    }

    public void updatePassword(String newPassword) {
        this.password = PasswordUtil.encryptPassword(newPassword, this.salt);
        this.updateTime = LocalDateTime.now();
    }
}
