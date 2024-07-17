package com.app.community.api.chat;

import com.app.community.domain.chat.Chat;
import com.app.community.domain.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.community.api.chat.ChatResponse.*;

@RequiredArgsConstructor
@RequestMapping("/api/chats")
@RestController
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/check/{respondentId}")
    public ResponseEntity<CheckChatResponse> checkAvailableChat(
            @AuthenticationPrincipal Long memberId,
            @PathVariable(name = "respondentId") Long respondentId
    ) {
        Chat chat = chatService.requestChat(respondentId, memberId);
        CheckChatResponse response = new CheckChatResponse(chat.getId(), chat.getIsEnd());
        return ResponseEntity.ok().body(response);
    }
}
