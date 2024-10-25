package com.app.community.api.controller.response;

import com.app.community.api.controller.request.MessageRequest;
import com.app.community.domain.model.chat.MessageType;

import java.time.LocalDateTime;

public class MessageRequests {

    public record SendMessageResponse(
            Long id,
            Long chatId,
            Sender sender,
            String body,
            LocalDateTime createdAt,
            MessageType messageType,
            Boolean isRead
    ) {

        public static SendMessageResponse toResponse(MessageRequest.SendMessageRequest request) {
            return new SendMessageResponse(
                    request.id(),
                    request.chatId(),
                    new Sender(request.senderId(), request.senderNickname()),
                    request.body(),
                    null,
                    null,
                    null
            );
        }
    }

    public record Sender(
            Long id,
            String nickname
    ) {
    }
}
