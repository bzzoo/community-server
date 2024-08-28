package com.app.community.support.oauth;

public interface OauthResponse {
    String getProviderName();
    String getUserId();
    String getEmail();
    String getProfileImagePath();
}
