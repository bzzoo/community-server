package com.app.community.api.chat;

import com.app.community.domain.chat.MessageService;
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
    public void sendMessage(MessageRequest.SendMessage request) {
        messageService.saveMessage(request.content(), request.chatId(), request.sender().memberId());
        messagingTemplate.convertAndSend("/topic/chat/" + request.chatId(), request);
    }
}
