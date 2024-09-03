package com.app.community.domain.agg.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatReadService {

    private final ChatRepositoryForQuery chatRepositoryForQuery;

    public List<ChatQuery.ChatInfo> getChatListByMemberId(Long memberId) {
        return chatRepositoryForQuery.findChatListByMemberId(memberId);
    }

    public List<ChatQuery.ChatMessageInfo> getChatMessageList(Long memberId, Long chatId, Long cursor) {
        return chatRepositoryForQuery.findChatMessageList(memberId, chatId, cursor);
    }
}
