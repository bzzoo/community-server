package com.app.community.domain.agg.chat;

import java.util.Optional;

public interface ChatRepository {
    Chat save(Chat chat);
    Optional<Chat> findById(Long chatId);
    Optional<Chat> findChatByParticipant(ChatParticipant participant);
}
