package com.app.community.storage.chat;

import com.app.community.domain.chat.Message;
import com.app.community.domain.chat.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MessageRepositoryImpl implements MessageRepository {

    private final MessageJpaRepository messageJpaRepository;

    @Override
    public void save(Message message) {
        messageJpaRepository.save(MessageEntity.fromDomain(message)).toDomain();
    }
}
