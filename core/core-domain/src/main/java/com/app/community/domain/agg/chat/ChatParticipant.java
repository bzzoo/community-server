package com.app.community.domain.agg.chat;

public record ChatParticipant(
        Long requesterId,
        Long respondentId
) {
}
