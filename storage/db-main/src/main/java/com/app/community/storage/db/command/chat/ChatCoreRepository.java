package com.app.community.storage.db.command.chat;

import com.app.community.domain.model.chat.Chat;
import com.app.community.domain.model.chat.ChatParticipant;
import com.app.community.domain.model.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ChatCoreRepository implements ChatRepository {

    private final ChatJpaRepository chatJpaRepository;

    @Override
    public Chat save(Chat chat) {
        ChatEntity chatEntity = new ChatEntity(
                chat.getId(),
                chat.getParticipant().respondentId(),
                chat.getParticipant().requesterId(),
                chat.getChatDateTime().createdAt(),
                chat.getChatDateTime().endAt(),
                chat.getStatus()
        );
        return chatJpaRepository.save(chatEntity).toDomain();
    }

    @Override
    public Optional<Chat> findById(Long chatId) {
        return chatJpaRepository.findById(chatId)
                .map(ChatEntity::toDomain);
    }

    @Override
    public Optional<Chat> findChatByParticipant(ChatParticipant participant) {
        return chatJpaRepository.findChatByParticipant(participant.requesterId(), participant.requesterId())
                .map(ChatEntity::toDomain);
    }
}
