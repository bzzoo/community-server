package com.app.community.support.oauth;

import java.util.Map;

public class OauthGoogleResponse implements OauthResponse {

    private final Map<String, Object> attribute;

    public OauthGoogleResponse(Map<String, Object> attributes) {
        this.attribute = attributes;
    }

    @Override
    public String getProviderName() {
        return "GOOGLE";
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getProfileImagePath() {
        return null;
    }

    @Override
    public String getUserId() {
        return attribute.get("sub").toString();
    }

}
