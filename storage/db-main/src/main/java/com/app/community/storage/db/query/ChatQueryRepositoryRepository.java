package com.app.community.storage.db.query;

import com.app.community.domain.query.ChatQuery.ChatMessageInfo;
import com.app.community.domain.query.ChatQuery.ChatSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ChatQueryRepositoryRepository implements com.app.community.domain.query.ChatQueryRepository {

    private final ChatMapper chatMapper;

    @Override
    public List<ChatSummary> findChatListByMemberId(Long memberId, int size, Long cursor) {
        return chatMapper.findChatListByMemberId(memberId, size, cursor);
    }

    @Override
    public List<ChatMessageInfo> findChatMessageList(Long memberId, Long chatId, int size, Long cursor) {
        return chatMapper.findChatMessageList(memberId, chatId, size, cursor);
    }
}