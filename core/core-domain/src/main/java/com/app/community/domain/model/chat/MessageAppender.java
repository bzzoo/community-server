package com.app.community.domain.model.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MessageAppender {

    private final MessageRepository messageRepository;

    public void save(String content, Chat chat, Long senderId) {
        Message message = Message.create(senderId, content, chat.getId());
        messageRepository.save(message);
    }
}
