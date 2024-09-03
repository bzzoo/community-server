package com.app.community.controller;

import com.app.community.domain.agg.chat.ChatQuery.*;
import com.app.community.domain.agg.chat.ChatReadService;
import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/chats")
@RestController
public class ChatQueryController {

    private final ChatReadService chatReadService;

    @GetMapping("")
    ResponseEntity<List<ChatInfo>> getMyChatList(
            @AuthenticationPrincipal Long memberId,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor
    ) {
        List<ChatInfo> chatList = chatReadService.getChatListByMemberId(memberId);
        return ResponseEntity.ok().body(chatList);
    }

    @GetMapping("/{chatId}/messages")
    ResponseEntity<CursorResult<ChatMessageInfo>> getChatMessageList(
            @AuthenticationPrincipal Long memberId,
            @PathVariable(name = "chatId") Long chatId,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor,
            @RequestParam(name = "s", required = false, defaultValue = "20") int size
    ) {
        List<ChatMessageInfo> chatMessages = chatReadService.getChatMessageList(memberId, chatId, cursor);
        return ResponseEntity.ok().body(CursorResult.of(chatMessages, size, ChatMessageInfo::getChatId));
    }
}

