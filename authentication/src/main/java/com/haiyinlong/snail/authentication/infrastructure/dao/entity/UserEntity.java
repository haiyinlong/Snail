package com.haiyinlong.snail.authentication.infrastructure.dao.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * UserEntity
 *
 * @author HaiYinLong
 * @version 2025/09/16 14:56
 **/
@Data
@TableName("user")
public class UserEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String salt;
    private Integer enabled;
    private Integer accountNonExpired;
    private Integer accountNonLocked;
    private Integer credentialsNonExpired;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
