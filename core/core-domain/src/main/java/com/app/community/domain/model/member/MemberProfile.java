package com.app.community.domain.model.member;

public record MemberProfile(
        String email,
        String profileImagePath,
        MemberNickname nickname,
        MemberSetting memberSetting,
        MemberPosition position
) {

    public static MemberProfile init(String email, String profileImagePath) {
        return new MemberProfile(
                email,
                profileImagePath,
                MemberNickname.init(),
                MemberSetting.init(),
                MemberPosition.NONE
        );
    }
}
