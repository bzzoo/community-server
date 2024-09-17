package com.app.community.controller;

import com.app.community.domain.agg.chat.ChatQuery.*;
import com.app.community.domain.agg.chat.ChatReadService;
import com.app.community.support.response.ApiResponse;
import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/chats")
@RestController
public class ChatQueryController {

    private final ChatReadService chatReadService;

    @GetMapping("")
    ApiResponse<CursorResult<ChatInfo>> getMyChatList(
            @AuthenticationPrincipal Long memberId,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor
    ) {
        var chatList = chatReadService.getChatListByMemberId(memberId, size, cursor);
        var cursorResult = CursorResult.of(chatList, size, ChatInfo::getChatId);
        return ApiResponse.success(cursorResult);
    }

    @GetMapping("/{chatId}/messages")
    ApiResponse<CursorResult<ChatMessageInfo>> getChatMessageList(
            @AuthenticationPrincipal Long memberId,
            @PathVariable(name = "chatId") Long chatId,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor
    ) {
        var chatMessages = chatReadService.getChatMessageList(memberId, chatId, size, cursor);
        var cursorResult = CursorResult.of(chatMessages, 20, ChatMessageInfo::getChatId);
        return ApiResponse.success(cursorResult);
    }
}

