package com.app.community.api.chat;

import com.app.community.domain.chat.ChatReadService;
import com.app.community.domain.chat.ChatSummary;
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
    public ResponseEntity<List<ChatSummary.ChatInfo>> getMyChatList(
            @AuthenticationPrincipal Long memberId
    ) {
        List<ChatSummary.ChatInfo> chatList = chatReadService.getChatListByMemberId(memberId);
        return ResponseEntity.ok().body(chatList);
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<CursorResult<ChatSummary.ChatMessageInfo>> getChatMessageList(
            @AuthenticationPrincipal  Long memberId,
            @PathVariable(name = "chatId") Long chatId,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor
    ){
        CursorResult<ChatSummary.ChatMessageInfo> chatMessageList
                = chatReadService.getChatMessageList(memberId, chatId, cursor);
        return ResponseEntity.ok().body(chatMessageList);
    }
}

