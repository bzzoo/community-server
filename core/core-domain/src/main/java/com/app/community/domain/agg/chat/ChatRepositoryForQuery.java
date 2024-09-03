package com.app.community.domain.agg.chat;

import java.util.List;

public interface ChatRepositoryForQuery {
    List<ChatQuery.ChatInfo> findChatListByMemberId(Long memberId);
    List<ChatQuery.ChatMessageInfo> findChatMessageList(Long memberId, Long chatId, Long cursor);
}