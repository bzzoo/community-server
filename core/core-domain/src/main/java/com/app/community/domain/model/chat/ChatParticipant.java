package com.app.community.domain.model.chat;

public record ChatParticipant(
        Long requesterId,
        Long respondentId
) {
}
