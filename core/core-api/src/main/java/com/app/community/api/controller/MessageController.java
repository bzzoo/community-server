package com.app.community.api.controller;

import com.app.community.api.controller.request.MessageRequest;
import com.app.community.api.controller.response.MessageRequests;
import com.app.community.domain.model.chat.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    void sendMessage(MessageRequest.SendMessageRequest request) {
        messageService.saveMessage(request.body(), request.chatId(), request.senderId());
        var response = MessageRequests.SendMessageResponse.toResponse(request);
        messagingTemplate.convertAndSend("/topic/chat/" + request.chatId(), response);
    }
}
