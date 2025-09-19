package com.haiyinlong.snail.authentication.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.haiyinlong.snail.authentication.domain.client.RegisteredClientAggregate;
import com.haiyinlong.snail.authentication.domain.client.repository.RegisteredClientRepository;
import com.haiyinlong.snail.authentication.infrastructure.dao.OAuth2RegisteredClientDao;
import com.haiyinlong.snail.authentication.infrastructure.dao.entity.OAuth2RegisteredClientEntity;

import lombok.RequiredArgsConstructor;

/**
 * RegisteredClientRepositoryImpl
 *
 * @author HaiYinLong
 * @version 2025/09/15 18:20
 **/
@Repository
@RequiredArgsConstructor
public class RegisteredClientRepositoryImpl implements RegisteredClientRepository {
    private final OAuth2RegisteredClientDao oAuth2RegisteredClientDao;

    @Override
    public void save(RegisteredClientAggregate registeredClientAggregate) {
        OAuth2RegisteredClientEntity oAuth2RegisteredClientEntity =
            convertOAuth2RegisteredClientToEntity(registeredClientAggregate);
        oAuth2RegisteredClientDao.insert(oAuth2RegisteredClientEntity);
    }

    private static OAuth2RegisteredClientEntity
        convertOAuth2RegisteredClientToEntity(RegisteredClientAggregate registeredClientAggregate) {
        OAuth2RegisteredClientEntity oAuth2RegisteredClientEntity = new OAuth2RegisteredClientEntity();
        oAuth2RegisteredClientEntity.setId(registeredClientAggregate.getId());
        oAuth2RegisteredClientEntity.setClientId(registeredClientAggregate.getClientId());
        oAuth2RegisteredClientEntity.setClientSecret(registeredClientAggregate.getClientSecret());
        oAuth2RegisteredClientEntity.setRawClientSecret(registeredClientAggregate.getRawClientSecret());
        oAuth2RegisteredClientEntity.setClientName(registeredClientAggregate.getClientName());
        oAuth2RegisteredClientEntity
            .setClientAuthenticationMethods(registeredClientAggregate.getClientAuthenticationMethods());
        oAuth2RegisteredClientEntity.setAuthorizationGrantTypes(registeredClientAggregate.getAuthorizationGrantTypes());
        oAuth2RegisteredClientEntity.setRedirectUris(registeredClientAggregate.getRedirectUris());
        oAuth2RegisteredClientEntity.setPostLogoutRedirectUris(registeredClientAggregate.getPostLogoutRedirectUris());
        oAuth2RegisteredClientEntity.setScopes(registeredClientAggregate.getScopes());
        oAuth2RegisteredClientEntity
            .setRequireAuthorizationConsent(registeredClientAggregate.isRequireAuthorizationConsent());
        oAuth2RegisteredClientEntity.setEnabledFlag(registeredClientAggregate.getEnabledFlag());
        oAuth2RegisteredClientEntity.setDeletedFlag(registeredClientAggregate.getDeletedFlag());
        oAuth2RegisteredClientEntity.setDescription(registeredClientAggregate.getDescription());
        oAuth2RegisteredClientEntity.setUpdateTime(registeredClientAggregate.getUpdateTime());
        oAuth2RegisteredClientEntity.setClientSettings(registeredClientAggregate.getClientSettings());
        oAuth2RegisteredClientEntity.setTokenSettings(registeredClientAggregate.getTokenSettings());
        return oAuth2RegisteredClientEntity;
    }
}
