package com.app.community.domain.agg.chat;

import com.app.community.domain.agg.member.Member;
import com.app.community.domain.agg.member.MemberReader;
import com.app.community.domain.agg.point.PointProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final MemberReader memberReader;
    private final ChatProcessor chatProcessor;
    private final PointProcessor pointProcessor;

    public Chat requestChat(ChatParticipant participant, ChatDateTime dateTime){
        Member respondent = memberReader.getById(participant.respondentId());
        Member requester = memberReader.getById(participant.requesterId());
        Chat chat = chatProcessor.createOrGet(participant, dateTime);
        //pointProcessor.processChatRequestTransaction(respondent.getId(), requester.getId(), chat.getId());
        return chat;
    }

}
