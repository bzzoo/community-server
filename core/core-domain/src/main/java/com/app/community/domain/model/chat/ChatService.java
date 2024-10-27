package com.app.community.domain.model.chat;

import com.app.community.domain.model.point.PointChatManager;
import com.app.community.domain.model.point.PointUpvotedManager;
import com.app.community.domain.model.member.Member;
import com.app.community.domain.model.member.MemberReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final MemberReader memberReader;
    private final ChatProcessor chatProcessor;
    private final PointChatManager PointChatManager;
    private final ChatValidator chatValidator;

    public Chat checkoutChat(ChatParticipant participant, ChatDateTime dateTime, int amount, int period) {
        var respondent = memberReader.getById(participant.respondentId());
        var requester = memberReader.getById(participant.requesterId());
        chatValidator.validateCheckout(requester, respondent, amount, period);
        var chat = chatProcessor.createOrGet(participant, dateTime);
        PointChatManager.handle(requester, respondent, chat);
        return chat;
    }
}
