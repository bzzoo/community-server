package com.app.community.storage.db.command.chat;


import com.app.community.domain.agg.chat.Message;
import com.app.community.domain.agg.chat.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MessageCoreRepository implements MessageRepository {

    private final MessageJpaRepository messageJpaRepository;

    @Override
    public void save(Message message) {
        messageJpaRepository.save(MessageEntity.fromDomain(message)).toDomain();
    }
}
