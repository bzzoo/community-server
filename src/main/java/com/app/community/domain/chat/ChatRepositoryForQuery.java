package com.app.community.domain.chat;

import java.util.List;

public interface ChatRepositoryForQuery {
    List<ChatSummary.ChatInfo> findChatListByMemberId(Long memberId);
    List<ChatSummary.ChatMessageInfo> findChatMessageList(Long memberId, Long chatId, Long cursor);
}