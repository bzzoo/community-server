package com.app.community.storage.db.query;

import com.app.community.domain.agg.chat.ChatQuery.ChatSummary;
import com.app.community.domain.agg.chat.ChatQuery.ChatMessageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {

    List<ChatSummary> findChatListByMemberId(
            @Param("memberId") Long memberId,
            @Param("size") int size,
            @Param("cursor") Long cursor
    );

    List<ChatMessageInfo> findChatMessageList(
            @Param("memberId") Long memberId,
            @Param("chatId") Long chatId,
            @Param("size") int size,
            @Param("cursor") Long cursor
    );
}