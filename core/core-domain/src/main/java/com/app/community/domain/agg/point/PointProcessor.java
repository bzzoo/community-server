package com.app.community.domain.agg.point;

import com.app.community.domain.agg.member.Member;
import com.app.community.domain.agg.member.MemberReader;
import com.app.community.domain.agg.member.MemberWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PointProcessor {

    private final MemberReader memberReader;
    private final MemberWriter memberWriter;
    private final PointValidator pointValidator;
    private final PointHistoryReader pointHistoryReader;
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

    public void rewardCommenting(Long writerId, Long commentId){
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

    public void rewardPostAuthorForRecommendation(Long rewardTargetId, Long referenceId, PointReferenceType type){
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

    public void processChatRequestTransaction(Long requesterId, Long respondentId, Long referenceId, PointReferenceType type){
        Member requester = memberReader.getById(requesterId);
        Member respondent = memberReader.getById(respondentId);
        requester.updatePoints(-1);
        respondent.updatePoints(1);
        pointHistoryWriter.create(
                new PointAccount(requesterId, requester.getGrade().value()),
                new PointAccount(requesterId, requester.getGrade().value()),
                new PointReference(referenceId, type),
                PointEventType.CHAT_PAYMENT,
                respondent.getProfile().memberSetting().chatPeePoint()
        );
    }
}
