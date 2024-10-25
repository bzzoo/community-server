package com.app.community.domain.model.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ChatProcessor {

    private final ChatReader chatReader;
    private final ChatWriter chatWriter;

    public Chat createOrGet(ChatParticipant participant, ChatDateTime dateTime) {
        Optional<Chat> chatOp = chatReader.findByParticipant(participant);
        return chatOp.orElseGet(() -> chatWriter.append(participant, dateTime));
    }
}
