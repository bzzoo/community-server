package com.app.community.domain.chat;

import java.time.LocalDateTime;

public class ChatSummary {

    public record ChatInfo(
            Long chatId,
            Participant sender,
            Participant receiver,
            String lastMessages,
            LocalDateTime lastUpdatedAt,
            LocalDateTime createdAt,
            LocalDateTime endDate
    ){
    }

    public record ChatMessageInfo(
            Long chatId,
            Long messageId,
            Participant sender,
            String content,
            LocalDateTime createdAt,
            Message.MessageType messageType,
            Boolean isRead
    ){}

    public record Participant(
            Long memberId,
            String nickname
    ){}
}
