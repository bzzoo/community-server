package com.app.community.api.chat;

import com.app.community.domain.chat.Message;

import java.time.LocalDateTime;

public class MessageRequest {

    public record SendMessage (
            Long chatId,
            Long messageId,
            Sender sender,
            String content,
            LocalDateTime createdAt,
            Message.MessageType messageType,
            Boolean isRead
    ){}

    public record Sender(
            Long memberId,
            String nickname
    ){}
}
