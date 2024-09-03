package com.app.community.domain.agg.chat;

import com.app.community.domain.support.error.CoreApiException;
import com.app.community.domain.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ChatReader {

    private final ChatRepository chatRepository;

    public Chat getById(Long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() -> new CoreApiException(ErrorType.DEFAULT_ERROR));
    }

    public Optional<Chat> findByParticipant(ChatParticipant participant) {
        return chatRepository.findChatByParticipant(participant);
    }
}
