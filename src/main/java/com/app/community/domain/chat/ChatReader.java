package com.app.community.domain.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ChatReader {

    private final ChatRepository chatRepository;

    public Optional<Chat> findByParticipantsId(Long respondentId, Long requestId){
        return chatRepository.findChatByParticipantIds(requestId, respondentId);
    }

    public Chat getById(Long chatId) {
        return chatRepository.findById(chatId).orElseThrow();
    }
}
