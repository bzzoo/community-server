package com.app.community.domain.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MessageAppender {

    private final MessageRepository messageRepository;

    public void append(String content, Chat chat, Long senderId) {
        Message message = Message.create(senderId, content, chat.getId());
        messageRepository.save(message);
    }
}
