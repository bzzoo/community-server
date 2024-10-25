package com.app.community.domain.model.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatWriter {

    private final ChatRepository chatRepository;

    public Chat append(ChatParticipant participant, ChatDateTime dateTime) {
        Chat newChat = Chat.create(participant, dateTime);
        return chatRepository.save(newChat);
    }
}
