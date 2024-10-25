package com.app.community.domain.model.chat;

import java.time.LocalDateTime;

public record ChatDateTime(
        LocalDateTime createdAt,
        LocalDateTime endAt
) {
    public static ChatDateTime ofPeriod(Integer period) {
        LocalDateTime now = LocalDateTime.now();
        return new ChatDateTime(now, now.plusDays(period));
    }
}

