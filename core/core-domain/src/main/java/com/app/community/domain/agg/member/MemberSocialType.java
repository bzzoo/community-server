package com.app.community.domain.agg.member;

import java.util.Arrays;
import java.util.List;

public enum MemberSocialType {
    KAKAO, GOOGLE;

    public static MemberSocialType from(String type){
        return Arrays.stream(MemberSocialType.values())
                .filter(socialType -> socialType.name().equalsIgnoreCase(type))
                .findFirst()
                .orElse(null);
    }

    public static boolean isSocialType(String type){
        List<MemberSocialType> memberSocialTypes = Arrays.stream(MemberSocialType.values())
                .filter(socialType -> socialType.name().equals(type))
                .toList();
        return !memberSocialTypes.isEmpty();
    }
}
