package com.app.community.domain.member;

import java.util.Arrays;
import java.util.List;

public enum MemberSocialType {
    KAKAO, GOOGLE;

    public static MemberSocialType from(String type){
        return MemberSocialType.valueOf(type.toUpperCase());
    }

    public static boolean isSocialType(String type){
        List<MemberSocialType> memberSocialTypes = Arrays.stream(MemberSocialType.values())
                .filter(socialType -> socialType.name().equals(type))
                .toList();
        return !memberSocialTypes.isEmpty();
    }
}
