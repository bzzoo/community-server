package com.app.community.domain.agg.chat;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Message {

    private Long id;
    private Long chatId;
    private Long senderId;
    private String content;
    private Boolean isRead;
    private MessageType messageType;

    @Builder
    private Message(
            Long id,
            Long chatId,
            Long senderId,
            String content,
            Boolean isRead,
            MessageType messageType
    ) {
        this.id = id;
        this.chatId = chatId;
        this.senderId = senderId;
        this.content = content;
        this.isRead = isRead;
        this.messageType = messageType;
    }

    public static Message create(Long senderId, String content, Long chatId) {
        return Message.builder()
                .chatId(chatId)
                .senderId(senderId)
                .messageType(MessageType.MESSAGE)
                .content(content)
                .isRead(false)
                .build();
    }
}
