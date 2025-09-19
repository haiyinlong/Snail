package com.haiyinlong.snail.authentication.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.haiyinlong.snail.authentication.domain.user.UserAggregate;
import com.haiyinlong.snail.authentication.domain.user.repository.UserRepository;
import com.haiyinlong.snail.authentication.infrastructure.converter.UserAggregateToEntityConverter;
import com.haiyinlong.snail.authentication.infrastructure.dao.UserDao;
import com.haiyinlong.snail.authentication.infrastructure.dao.UserProfileDao;
import com.haiyinlong.snail.authentication.infrastructure.dao.entity.UserEntity;
import com.haiyinlong.snail.authentication.infrastructure.dao.entity.UserProfileEntity;

import lombok.RequiredArgsConstructor;

/**
 * UserRepositoryImpl
 *
 * @author HaiYinLong
 * @version 2025/09/16 15:09
 **/
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserDao userDao;
    private final UserProfileDao userProfileDao;

    @Override
    public void saveUser(UserAggregate userAggregate) {
        UserEntity userEntity = UserAggregateToEntityConverter.INSTANCE.convert(userAggregate);
        userDao.insert(userEntity);
        userAggregate.setId(userEntity.getId());
        // 保存用户信息
        UserProfileEntity userProfileEntity = UserAggregateToEntityConverter.INSTANCE.convertProfile(userAggregate);
        userProfileEntity.setUserId(userEntity.getId());
        userProfileDao.insert(userProfileEntity);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    @Override
    public UserAggregate getByUsername(String username) {
        UserEntity userEntity = userDao.getByUsername(username);
        if (null == userEntity) {
            return null;
        }
        UserProfileEntity userProfileEntity = userProfileDao.getByUserId(userEntity.getId());
        UserAggregate userAggregate = UserAggregate.of(userEntity.getId(), userEntity.getUsername(),
            userEntity.getPassword(), userEntity.getSalt(), userEntity.getAccountNonExpired(),
            userEntity.getAccountNonLocked(), userEntity.getCredentialsNonExpired(), userEntity.getEnabled(),
            userEntity.getCreateTime(), userEntity.getUpdateTime());
        userAggregate.updateProfile(userProfileEntity.getRealName(), userProfileEntity.getEmail(),
            userProfileEntity.getPhone(), userProfileEntity.getUserType(), userProfileEntity.getDepartmentId(),
            userProfileEntity.getDepartmentName(), userProfileEntity.getPosition(), userProfileEntity.getAuthorities());
        return userAggregate;
    }

    @Override
    public UserAggregate getById(Long userId) {
        UserEntity userEntity = userDao.selectById(userId);
        if (userEntity == null) {
            return null;
        }
        UserProfileEntity userProfileEntity = userProfileDao.getByUserId(userEntity.getId());
        UserAggregate userAggregate = UserAggregate.of(userEntity.getId(), userEntity.getUsername(),
            userEntity.getPassword(), userEntity.getSalt(), userEntity.getAccountNonExpired(),
            userEntity.getAccountNonLocked(), userEntity.getCredentialsNonExpired(), userEntity.getEnabled(),
            userEntity.getCreateTime(), userEntity.getUpdateTime());
        userAggregate.updateProfile(userProfileEntity.getRealName(), userProfileEntity.getEmail(),
            userProfileEntity.getPhone(), userProfileEntity.getUserType(), userProfileEntity.getDepartmentId(),
            userProfileEntity.getDepartmentName(), userProfileEntity.getPosition(), userProfileEntity.getAuthorities());
        return userAggregate;
    }

    @Override
    public void updatePassword(UserAggregate userAggregate) {
        UserEntity userEntity = userDao.selectById(userAggregate.getId());
        userEntity.setPassword(userAggregate.getPassword());
        userEntity.setUpdateTime(userAggregate.getUpdateTime());
        userDao.updateById(userEntity);
    }
}
