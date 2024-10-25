package com.app.community.api.controller.response;

import com.app.community.domain.model.chat.ChatStatus;

public class ChatResponses {

    public record CheckChatResponse(
            Long chatId,
            ChatStatus status
    ){
    }
}
