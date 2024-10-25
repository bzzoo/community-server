package com.app.community.api.controller.request;

public class MessageRequest {

    public record SendMessageRequest(
            Long id,
            Long chatId,
            Long senderId,
            String senderNickname,
            String body
    ){}
}
