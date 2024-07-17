package com.app.community.storage.chat;

import com.app.community.domain.chat.ChatRepositoryForQuery;
import com.app.community.domain.chat.ChatSummary;
import com.app.community.storage.member.QMemberEntity;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.community.storage.chat.QChatEntity.chatEntity;
import static com.app.community.storage.chat.QMessageEntity.messageEntity;

@RequiredArgsConstructor
@Repository
public class ChatQueryRepository implements ChatRepositoryForQuery {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChatSummary.ChatInfo> findChatListByMemberId(Long memberId) {
        QMemberEntity requester = new QMemberEntity("requester");
        QMemberEntity respondent = new QMemberEntity("respondent");

        QMessageEntity latestMessage = new QMessageEntity("latestMessage");
        var latestMessageIdSubQuery = JPAExpressions
                .select(latestMessage.id.max())
                .from(latestMessage)
                .where(latestMessage.chatId.eq(chatEntity.id));

        return queryFactory
                .select(Projections.constructor(ChatSummary.ChatInfo.class,
                        chatEntity.id,
                        Projections.constructor(ChatSummary.Participant.class, requester.id, requester.nickname),
                        Projections.constructor(ChatSummary.Participant.class, respondent.id, respondent.nickname),
                        messageEntity.content,
                        messageEntity.createdAt,
                        chatEntity.createdAt,
                        chatEntity.endDate))
                .from(chatEntity)
                .leftJoin(messageEntity).on(messageEntity.id.eq(latestMessageIdSubQuery))
                .leftJoin(requester).on(chatEntity.requesterId.eq(requester.id))
                .leftJoin(respondent).on(chatEntity.respondentId.eq(respondent.id))
                .where(chatEntity.requesterId.eq(memberId).or(chatEntity.respondentId.eq(memberId)))
                .groupBy(chatEntity.id)
                .fetch();
    }

    @Override
    public List<ChatSummary.ChatMessageInfo> findChatMessageList(Long memberId, Long chatId, Long cursor) {
        QMemberEntity sender = new QMemberEntity("sender");
        List<Tuple> queryResult = queryFactory
                .select(
                        messageEntity.id,
                        sender.id,
                        sender.nickname,
                        messageEntity.content,
                        messageEntity.createdAt,
                        messageEntity.messageType,
                        messageEntity.isRead)
                .from(messageEntity)
                .leftJoin(sender).on(sender.id.eq(messageEntity.senderId))
                .where(messageEntity.chatId.eq(chatId)
                        .and(cursor != null ? messageEntity.id.gt(cursor) : null))
                .orderBy(messageEntity.createdAt.desc())
                .limit(20)
                .fetch();

        return queryResult.stream().map(tuple -> new ChatSummary.ChatMessageInfo(
                tuple.get(messageEntity.chatId),
                tuple.get(messageEntity.id),
                new ChatSummary.Participant(tuple.get(sender.id), tuple.get(sender.nickname)),
                tuple.get(messageEntity.content),
                tuple.get(messageEntity.createdAt),
                tuple.get(messageEntity.messageType),
                tuple.get(messageEntity.isRead)
        )).collect(Collectors.toList());
    }
}
