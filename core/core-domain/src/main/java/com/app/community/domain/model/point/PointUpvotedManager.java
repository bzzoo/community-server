package com.app.community.domain.model.point;

import com.app.community.domain.model.member.Member;
import com.app.community.domain.model.member.MemberReader;
import com.app.community.domain.model.member.MemberWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PointUpvotedManager {

    private static final int ARTICLE_UPVOTED_POINT = 5;
    private static final int COMMENT_UPVOTED_POINT = 1;

    private final MemberReader memberReader;
    private final MemberWriter memberWriter;
    private final PointHistoryWriter pointHistoryWriter;

    public void handleUpvotedArticle(Long rewardTargetId, Long referenceId) {
        Member rewardTarget = memberReader.getById(rewardTargetId);
        rewardTarget.updatePoints(ARTICLE_UPVOTED_POINT);
        memberWriter.update(rewardTarget);
        pointHistoryWriter.create(
                PointAccount.fromAccountNoting(),
                PointAccount.toAccount(rewardTargetId, rewardTarget.getGrade().value()),
                new PointReference(referenceId, PointReferenceType.ARTICLE),
                PointEventType.ARTICLE_RECOMMENDATION,
                ARTICLE_UPVOTED_POINT
        );
    }

    public void handleUpvotedComment(Long rewardTargetId, Long referenceId) {
        Member rewardTarget = memberReader.getById(rewardTargetId);
        rewardTarget.updatePoints(COMMENT_UPVOTED_POINT);
        memberWriter.update(rewardTarget);
        pointHistoryWriter.create(
                PointAccount.fromAccountNoting(),
                PointAccount.toAccount(rewardTargetId, rewardTarget.getGrade().value()),
                new PointReference(referenceId, PointReferenceType.COMMENT),
                PointEventType.ARTICLE_RECOMMENDATION,
                ARTICLE_UPVOTED_POINT
        );
    }
}
