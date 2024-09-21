package com.app.community.domain.agg.chat;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChatQuery {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatSummary {
        private Long id;
        private Participant sender;
        private Participant receiver;
        private String lastMessage;
        private LocalDateTime lastUpdatedAt;
        private ChatDateTimeInfo period;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatMessageInfo {
        private Long id;
        private Long chatId;
        private Participant sender;
        private String body;
        private LocalDateTime createdAt;
        private MessageType messageType;
        private Boolean isRead;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Participant {
        private Long id;
        private String nickname;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatDateTimeInfo{
        private LocalDateTime createdAt;
        private LocalDateTime endDate;
    }
}
