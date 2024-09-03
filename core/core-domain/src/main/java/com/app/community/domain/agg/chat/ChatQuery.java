package com.app.community.domain.agg.chat;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ChatQuery {

    @Getter
    @AllArgsConstructor
    public static class ChatInfo {
        private Long chatId;
        private Participant sender;
        private Participant receiver;
        private String lastMessages;
        private LocalDateTime lastUpdatedAt;
        private LocalDateTime createdAt;
        private LocalDateTime endDate;

    }

    @Getter
    @AllArgsConstructor
    public static class ChatMessageInfo {
        private Long chatId;
        private Long messageId;
        private Participant sender;
        private String content;
        private LocalDateTime createdAt;
        private MessageType messageType;
        private Boolean isRead;

    }

    @Getter
    @AllArgsConstructor
    public static class Participant {
        private Long memberId;
        private String nickname;
    }
}
