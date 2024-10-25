package com.app.community.domain.model.chat;

import com.app.community.domain.support.error.DomainException;
import com.app.community.domain.support.error.DomainErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ChatReader {

    private final ChatRepository chatRepository;

    public Chat getById(Long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() -> new DomainException(DomainErrorType.DEFAULT_ERROR));
    }

    public Optional<Chat> findByParticipant(ChatParticipant participant) {
        return chatRepository.findChatByParticipant(participant);
    }
}
