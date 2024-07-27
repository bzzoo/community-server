package com.app.community.domain.chat;

import com.app.community.domain.chat.ChatSummary.*;
import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatReadService {

    private final ChatRepositoryForQuery chatRepositoryForQuery;

    public List<ChatInfo> getChatListByMemberId(Long memberId) {
        return chatRepositoryForQuery.findChatListByMemberId(memberId);
    }

    public CursorResult<ChatMessageInfo> getChatMessageList(Long memberId, Long chatId, Long cursor) {
        List<ChatMessageInfo> chatMessageList = chatRepositoryForQuery.findChatMessageList(memberId, chatId, cursor);
        return CursorResult.of(chatMessageList, 20, ChatMessageInfo::messageId);
    }
}
