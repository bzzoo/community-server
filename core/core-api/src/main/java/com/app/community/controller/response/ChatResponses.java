package com.app.community.controller.response;

import com.app.community.domain.agg.chat.ChatStatus;

public class ChatResponses {

    public record CheckChatResponse(
            Long chatId,
            ChatStatus status
    ){

    }
}
