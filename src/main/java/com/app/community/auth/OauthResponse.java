package com.app.community.auth;

public interface OauthResponse {
    String getProviderName();
    String getUserId();
    String getEmail();
    String getProfileImagePath();
}
