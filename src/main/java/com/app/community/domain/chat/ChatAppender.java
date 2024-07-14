package com.app.community.domain.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class ChatAppender {

    private final ChatRepository chatRepository;

    @Transactional
    public Chat create(Long respondentId, Long requesterId) {
        Chat newChat = Chat.create(respondentId, requesterId);
        return chatRepository.save(newChat);
    }
}
