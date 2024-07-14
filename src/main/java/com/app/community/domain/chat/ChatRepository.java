package com.app.community.domain.chat;

import java.util.Optional;

public interface ChatRepository {
    Chat save(Chat chat);
    Optional<Chat> findById(Long chatId);
    Optional<Chat> findChatByParticipantIds(Long respondentId, Long requestId);
}
