package com.app.community.domain.model.point;


import com.app.community.domain.model.member.Member;
import com.app.community.domain.model.member.MemberReader;
import com.app.community.domain.model.member.MemberWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PointContentManager {

    private static final int ARTICLE_CREATE_POINT = 10;
    private static final int COMMENT_CREATE_POINT = 1;

    private final MemberReader memberReader;
    private final MemberWriter memberWriter;
    private final PointHistoryWriter pointHistoryWriter;

    public void handlePosting(Long writerId, Long articleId) {
        Member rewardTarget = memberReader.getById(writerId);
        rewardTarget.updatePoints(ARTICLE_CREATE_POINT);
        memberWriter.update(rewardTarget);
        pointHistoryWriter.create(
                PointAccount.fromAccountNoting(),
                PointAccount.toAccount(writerId, rewardTarget.getGrade().value()),
                new PointReference(articleId, PointReferenceType.ARTICLE),
                PointEventType.ARTICLE_CREATION,
                ARTICLE_CREATE_POINT
        );
    }

    public void handleCommenting(Long writerId, Long commentId) {
        Member rewardTarget = memberReader.getById(writerId);
        rewardTarget.updatePoints(COMMENT_CREATE_POINT);
        memberWriter.update(rewardTarget);
        pointHistoryWriter.create(
                PointAccount.fromAccountNoting(),
                PointAccount.toAccount(writerId, rewardTarget.getGrade().value()),
                new PointReference(commentId, PointReferenceType.COMMENT),
                PointEventType.ARTICLE_CREATION,
                COMMENT_CREATE_POINT
        );
    }
}
