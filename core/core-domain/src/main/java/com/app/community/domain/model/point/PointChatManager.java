package com.app.community.domain.model.point;

import com.app.community.domain.model.chat.Chat;
import com.app.community.domain.model.member.Member;
import com.app.community.domain.model.member.MemberReader;
import com.app.community.domain.model.member.MemberWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PointChatManager {

    private final MemberWriter memberWriter;
    private final PointHistoryWriter pointHistoryWriter;

    public void handle(Member requester, Member respondent, Chat chat) {
        int chatPee = respondent.getProfile().memberSetting().chatPeePoint();
        requester.updatePoints(-(chatPee));
        respondent.updatePoints(chatPee);
        memberWriter.update(requester);
        memberWriter.update(respondent);
        pointHistoryWriter.create(
                new PointAccount(requester.getId(), requester.getGrade().value()),
                new PointAccount(requester.getId(), requester.getGrade().value()),
                new PointReference(chat.getId(), PointReferenceType.CHAT),
                PointEventType.CHAT_CONNECTION,
                chatPee
        );
    }
}
