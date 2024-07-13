package com.app.community.domain.chat;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class Message {

    private @Nullable Long id;
    private @NotNull Long chatId;
    private @NotNull Long senderId;
    private @Nullable String content;
    private @NotNull Boolean isRead;
    private @NotNull MessageType messageType;

    @Builder
    private Message(
            @Nullable Long id,
            @NotNull Long chatId,
            @NotNull Long senderId,
            @Nullable String content,
            @NotNull Boolean isRead,
            @NotNull MessageType messageType
    ) {
        this.id = id;
        this.chatId = chatId;
        this.senderId = senderId;
        this.content = content;
        this.isRead = isRead;
        this.messageType = messageType;
    }

    public enum MessageType {
        MESSAGE;
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
