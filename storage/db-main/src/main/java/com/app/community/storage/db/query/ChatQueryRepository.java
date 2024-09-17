package com.app.community.storage.db.query;

import com.app.community.domain.agg.chat.ChatQuery.*;
import com.app.community.domain.agg.chat.ChatRepositoryForQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ChatQueryRepository implements ChatRepositoryForQuery {

    private final ChatMapper chatMapper;

    @Override
    public List<ChatInfo> findChatListByMemberId(Long memberId, int size, Long cursor) {
        return chatMapper.findChatListByMemberId(memberId, size, cursor);
    }

    @Override
    public List<ChatMessageInfo> findChatMessageList(Long memberId, Long chatId, int size, Long cursor) {
        return chatMapper.findChatMessageList(memberId, chatId, size, cursor);
    }
}