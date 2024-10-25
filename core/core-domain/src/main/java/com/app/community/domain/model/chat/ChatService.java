package com.app.community.domain.model.chat;

import com.app.community.domain.model.point.PointProcessor;
import com.app.community.domain.model.member.Member;
import com.app.community.domain.model.member.MemberReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final MemberReader memberReader;
    private final ChatProcessor chatProcessor;
    private final PointProcessor pointProcessor;
    private final ChatValidator chatValidator;

    public Chat checkoutChat(ChatParticipant participant, ChatDateTime dateTime, int amount, int period) {
        Member respondent = memberReader.getById(participant.respondentId());
        Member requester = memberReader.getById(participant.requesterId());
        chatValidator.validateCheckout(requester, respondent, amount, period);
        Chat chat = chatProcessor.createOrGet(participant, dateTime);
        pointProcessor.processChatRequestTransaction(respondent, requester, chat);
        return chat;
    }
}
