package com.app.community.domain.agg.chat;

import com.app.community.domain.agg.chat.ChatQuery.*;

import java.util.List;

public interface ChatRepositoryForQuery {
    List<ChatSummary> findChatListByMemberId(Long memberId, int size, Long cursor);
    List<ChatMessageInfo> findChatMessageList(Long memberId, Long chatId, int size, Long cursor);
}