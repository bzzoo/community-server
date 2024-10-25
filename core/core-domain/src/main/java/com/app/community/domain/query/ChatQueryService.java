package com.app.community.domain.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatQueryService {

    private final ChatQueryRepository chatQueryRepository;

    public List<ChatQuery.ChatSummary> getChatListByMemberId(Long memberId, int size, Long cursor) {
        return chatQueryRepository.findChatListByMemberId(memberId, size, cursor);
    }

    public List<ChatQuery.ChatMessageInfo> getChatMessageList(Long memberId, Long chatId, int size, Long cursor) {
        return chatQueryRepository.findChatMessageList(memberId, chatId, size, cursor);
    }
}
