package com.app.community.controller;

import com.app.community.domain.agg.chat.ChatQuery.*;
import com.app.community.domain.agg.chat.ChatReadService;
import com.app.community.domain.agg.member.LoginMember;
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
    ApiResponse<CursorResult<ChatSummary>> getMyChatList(
            @AuthenticationPrincipal LoginMember member,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor
    ) {
        var chatList = chatReadService.getChatListByMemberId(member.memberId(), size, cursor);
        var cursorResult = CursorResult.of(chatList, size, ChatSummary::getId);
        return ApiResponse.success(cursorResult);
    }

    @GetMapping("/{chatId}/messages")
    ApiResponse<CursorResult<ChatMessageInfo>> getChatMessageList(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable(name = "chatId") Long chatId,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor
    ) {
        var chatMessages = chatReadService.getChatMessageList(member.memberId(), chatId, size, cursor);
        var cursorResult = CursorResult.of(chatMessages, size, ChatMessageInfo::getChatId);
        return ApiResponse.success(cursorResult);
    }
}

