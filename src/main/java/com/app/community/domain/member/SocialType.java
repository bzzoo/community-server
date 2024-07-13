package com.app.community.domain.member;

import java.util.Arrays;
import java.util.List;

public enum SocialType {
    KAKAO, GOOGLE;

    public static SocialType from(String type){
        return SocialType.valueOf(type.toUpperCase());
    }

    public static boolean isSocialType(String type){
        List<SocialType> socialTypes = Arrays.stream(SocialType.values())
                .filter(socialType -> socialType.name().equals(type))
                .toList();
        return !socialTypes.isEmpty();
    }
}
