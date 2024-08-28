
package com.app.community.support.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

public class OauthKakaoResponse implements OauthResponse {

    private final Map<String, Object> attribute;

    public OauthKakaoResponse(Map<String, Object> attributes) {
        this.attribute = attributes;
    }

    @Getter
    @AllArgsConstructor
    public static class KakaoAccount {
        private Boolean profileNicknameNeedsAgreement;
        private Profile profile;

        @Getter
        @AllArgsConstructor
        public static class Profile {
            private String username;
            private String thumbnail_image_url;

            public static Profile from(Map<String, Object> profile) {
                return new Profile(
                        String.valueOf(profile.get("nickname")),
                        String.valueOf(profile.get("profile_image_url"))
                );
            }
        }

        public static KakaoAccount from(Map<String, Object> attributes) {
            return new KakaoAccount(
                    Boolean.valueOf(String.valueOf(attributes.get("profile_nickname_needs_agreement"))),
                    Profile.from((Map<String, Object>) attributes.get("profile"))
            );
        }
    }

    public KakaoAccount getKakaoAccount() {
        return KakaoAccount.from((Map<String, Object>) attribute.get("kakao_account"));
    }

    @Override
    public String getProviderName() {
        return "KAKAO";
    }

    @Override
    public String getEmail() {
        return "";
    }

    @Override
    public String getProfileImagePath() {
        return getKakaoAccount().getProfile().getThumbnail_image_url();
    }

    @Override
    public String getUserId() {
        return attribute.get("id").toString();
    }
}
