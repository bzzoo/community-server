package com.app.community.domain.model.member;

import java.time.Instant;
import java.util.Random;

public record MemberNickname(
        String value
) {
    private static final Random random = new Random();

    public static MemberNickname init() {
        long timestamp = Instant.now().toEpochMilli();
        int randomNum = random.nextInt(1000);
        String name = "Member" + timestamp + "_" + String.format("%03d", randomNum);
        return new MemberNickname(name);
    }
}
