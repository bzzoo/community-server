package com.app.community.api.controller;

import com.app.community.api.support.response.ApiResponse;
import com.app.community.api.support.response.CursorResult;
import com.app.community.domain.query.ChatQuery;
import com.app.community.domain.query.ChatQueryService;
import com.app.community.domain.model.member.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/chats")
@RestController
public class ChatQueryController {

    private final ChatQueryService chatQueryService;

    @GetMapping("")
    ApiResponse<CursorResult<ChatQuery.ChatSummary>> getMyChatList(
            @AuthenticationPrincipal LoginMember member,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor
    ) {
        var chatList = chatQueryService.getChatListByMemberId(member.memberId(), size + 1, cursor);
        var cursorResult = CursorResult.of(chatList, size, ChatQuery.ChatSummary::getId);
        return ApiResponse.success(cursorResult);
    }

    @GetMapping("/{chatId}/messages")
    ApiResponse<CursorResult<ChatQuery.ChatMessageInfo>> getChatMessageList(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable(name = "chatId") Long chatId,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor
    ) {
        var chatMessages = chatQueryService.getChatMessageList(member.memberId(), chatId, size + 1, cursor);
        var cursorResult = CursorResult.of(chatMessages, size, ChatQuery.ChatMessageInfo::getId);
        return ApiResponse.success(cursorResult);
    }
}

