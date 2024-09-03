package com.app.community.storage.db.query;

import com.app.community.domain.agg.chat.ChatQuery.ChatInfo;
import com.app.community.domain.agg.chat.ChatQuery.ChatMessageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {

    List<ChatInfo> findChatListByMemberId(@Param("memberId") Long memberId);
    List<ChatMessageInfo> findChatMessageList(@Param("memberId") Long memberId, @Param("chatId") Long chatId, @Param("cursor") Long cursor);
}