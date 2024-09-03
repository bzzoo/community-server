package com.app.community.controller.request;

import com.app.community.domain.agg.chat.MessageType;

import java.time.LocalDateTime;

public class MessageRequest {

    public record SendMessage (
            Long chatId,
            Long messageId,
            Sender sender,
            String content,
            LocalDateTime createdAt,
            MessageType messageType,
            Boolean isRead
    ){}

    public record Sender(
            Long memberId,
            String nickname
    ){}
}
