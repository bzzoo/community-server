package com.app.community.domain.model.point;

import com.app.community.domain.model.chat.Chat;
import com.app.community.domain.model.member.Member;
import com.app.community.domain.model.member.MemberReader;
import com.app.community.domain.model.member.MemberWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PointProcessor {

    private final MemberReader memberReader;
    private final MemberWriter memberWriter;
    private final PointHistoryWriter pointHistoryWriter;

    public void rewardPosting(Long writerId, Long articleId) {
        Member rewardTarget = memberReader.getById(writerId);
        rewardTarget.updatePoints(5);
        memberWriter.update(rewardTarget);
        pointHistoryWriter.create(
                PointAccount.bySystem(),
                new PointAccount(writerId, rewardTarget.getGrade().value()),
                new PointReference(articleId, PointReferenceType.ARTICLE),
                PointEventType.ARTICLE_CREATION,
                5
        );
    }

    public void rewardCommenting(Long writerId, Long commentId) {
        Member rewardTarget = memberReader.getById(writerId);
        rewardTarget.updatePoints(1);
        memberWriter.update(rewardTarget);
        pointHistoryWriter.create(
                PointAccount.bySystem(),
                new PointAccount(writerId, rewardTarget.getGrade().value()),
                new PointReference(commentId, PointReferenceType.COMMENT),
                PointEventType.ARTICLE_CREATION,
                1
        );
    }

    public void rewardPostAuthorForRecommendation(Long rewardTargetId, Long referenceId, PointReferenceType type) {
        Member rewardTarget = memberReader.getById(rewardTargetId);
        rewardTarget.updatePoints(1);
        memberWriter.update(rewardTarget);
        pointHistoryWriter.create(
                PointAccount.bySystem(),
                new PointAccount(rewardTargetId, rewardTarget.getGrade().value()),
                new PointReference(referenceId, type),
                PointEventType.ARTICLE_RECOMMENDATION,
                1
        );
    }

    public void processChatRequestTransaction(Member requester, Member respondent, Chat chat) {
        int chatPee = respondent.getProfile().memberSetting().chatPeePoint();
        requester.updatePoints(-(chatPee));
        respondent.updatePoints(chatPee);
        pointHistoryWriter.create(
                new PointAccount(requester.getId(), requester.getGrade().value()),
                new PointAccount(requester.getId(), requester.getGrade().value()),
                new PointReference(chat.getId(), PointReferenceType.CHAT),
                PointEventType.CHAT_PAYMENT,
                chatPee
        );
    }
}
