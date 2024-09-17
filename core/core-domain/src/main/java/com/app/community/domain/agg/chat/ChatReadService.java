package com.app.community.domain.agg.chat;

import com.app.community.domain.agg.chat.ChatQuery.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatReadService {

    private final ChatRepositoryForQuery chatRepositoryForQuery;

    public List<ChatInfo> getChatListByMemberId(Long memberId, int size, Long cursor) {
        return chatRepositoryForQuery.findChatListByMemberId(memberId, size, cursor);
    }

    public List<ChatMessageInfo> getChatMessageList(Long memberId, Long chatId, int size, Long cursor) {
        return chatRepositoryForQuery.findChatMessageList(memberId, chatId, size, cursor);
    }
}
