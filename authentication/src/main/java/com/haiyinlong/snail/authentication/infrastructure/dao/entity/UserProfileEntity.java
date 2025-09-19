package com.haiyinlong.snail.authentication.infrastructure.dao.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * UserProfileEntity
 *
 * @author HaiYinLong
 * @version 2025/09/16 14:56
 **/
@Data
@TableName("user_profile")
public class UserProfileEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String userType;
    private String realName;
    private String email;
    private String phone;
    private Integer departmentId;
    private String departmentName;
    private String position;
    private String avatar;
    private String authorities;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
