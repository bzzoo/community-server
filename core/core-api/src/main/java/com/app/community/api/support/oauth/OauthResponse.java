package com.app.community.api.support.oauth;

public interface OauthResponse {
    String getProviderName();
    String getUserId();
    String getEmail();
    String getProfileImagePath();
}
