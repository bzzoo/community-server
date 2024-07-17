package com.app.community.storage.chat;

import com.app.community.domain.chat.Chat;
import com.app.community.domain.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ChatRepositoryImpl implements ChatRepository {

    private final ChatJpaRepository chatJpaRepository;

    @Override
    public Chat save(Chat chat) {
        return chatJpaRepository.save(ChatEntity.fromDomain(chat)).toDomain();
    }

    @Override
    public Optional<Chat> findById(Long chatId) {
        return chatJpaRepository.findById(chatId)
                .map(ChatEntity::toDomain);
    }

    @Override
    public Optional<Chat> findChatByParticipantIds(Long respondentId, Long requestId) {
        return chatJpaRepository.findChatByParticipantsId(requestId, respondentId)
                .map(ChatEntity::toDomain);
    }
}
