package com.app.community.api.chat;

public class ChatResponse {

    public record CheckChatResponse(
            Long chatId,
            boolean isEnd
    ){
    }
}
