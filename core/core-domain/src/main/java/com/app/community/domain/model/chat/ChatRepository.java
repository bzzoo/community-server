package com.app.community.domain.model.chat;

import java.util.Optional;

public interface ChatRepository {
    Chat save(Chat chat);
    Optional<Chat> findById(Long chatId);
    Optional<Chat> findChatByParticipant(ChatParticipant participant);
}
