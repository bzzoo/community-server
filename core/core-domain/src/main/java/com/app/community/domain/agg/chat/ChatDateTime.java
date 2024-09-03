package com.app.community.domain.agg.chat;

import java.time.LocalDateTime;

public record ChatDateTime(
        LocalDateTime createdAt,
        LocalDateTime endAt
) {
}
