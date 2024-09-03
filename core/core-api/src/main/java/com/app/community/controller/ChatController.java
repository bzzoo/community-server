package com.app.community.controller;

import com.app.community.domain.agg.chat.Chat;
import com.app.community.domain.agg.chat.ChatService;
import com.app.community.domain.agg.member.LoginMember;
import com.app.community.controller.request.ChatRequests.*;
import com.app.community.controller.response.ChatResponses.CheckChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/chats")
@RestController
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/")
    ResponseEntity<CheckChatResponse> createOrGet(
            @AuthenticationPrincipal LoginMember member,
            @RequestBody ChatRequest request
    ) {
        Chat chat = chatService.requestChat(
                request.toParticipant(),
                request.toDatetime()
        );
        CheckChatResponse response = new CheckChatResponse(chat.getId(), chat.getStatus());
        return ResponseEntity.ok().body(response);
    }
}
