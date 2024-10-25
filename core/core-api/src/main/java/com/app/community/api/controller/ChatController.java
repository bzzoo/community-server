package com.app.community.api.controller;

import com.app.community.api.controller.request.ChatRequests;
import com.app.community.api.controller.response.ChatResponses;
import com.app.community.domain.model.chat.Chat;
import com.app.community.domain.model.chat.ChatService;
import com.app.community.domain.model.member.LoginMember;
import com.app.community.api.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/chats")
@RestController
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/checkout")
    ApiResponse<ChatResponses.CheckChatResponse> checkoutChat(
            @AuthenticationPrincipal LoginMember member,
            @RequestBody ChatRequests.CheckoutRequest request
    ) {
        Chat chat = chatService.checkoutChat(
                request.toParticipant(),
                request.toDatetime(),
                request.amount(),
                request.period()
        );
        ChatResponses.CheckChatResponse response = new ChatResponses.CheckChatResponse(chat.getId(), chat.getStatus());
        return ApiResponse.success(response);
    }
}
