package com.app.community.domain.query;

import java.util.List;

public interface ChatQueryRepository {
    List<ChatQuery.ChatSummary> findChatListByMemberId(Long memberId, int size, Long cursor);
    List<ChatQuery.ChatMessageInfo> findChatMessageList(Long memberId, Long chatId, int size, Long cursor);
}