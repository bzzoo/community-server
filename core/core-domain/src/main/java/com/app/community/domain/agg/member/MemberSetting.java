package com.app.community.domain.agg.member;

public record MemberSetting(
        int chatPeePoint,
        boolean chatRefusal
) {
    private static final int DEFAULT_CHAT_PEE_POINT = 10;
    private static final boolean DEFAULT_CHAT_REFUSAL = false;

    public static MemberSetting init() {
        return new MemberSetting(DEFAULT_CHAT_PEE_POINT, DEFAULT_CHAT_REFUSAL);
    }
}
