package com.app.community.domain.query;

import java.time.LocalDateTime;

import com.app.community.domain.model.chat.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
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
