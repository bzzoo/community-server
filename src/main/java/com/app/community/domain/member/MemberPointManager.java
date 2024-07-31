package com.app.community.domain.member;

import com.app.community.domain.aritcle.Article;
import com.app.community.domain.comment.CommentTarget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class MemberPointManager {

    private static final Map<PointHistory.PointTransactionType, Integer> POINT_VALUES = new EnumMap<>(PointHistory.PointTransactionType.class);
    static {
        POINT_VALUES.put(PointHistory.PointTransactionType.SHARE_ARTICLE_CREATION, 10);
        POINT_VALUES.put(PointHistory.PointTransactionType.QUESTION_COMMENT_CREATION, 5);
        POINT_VALUES.put(PointHistory.PointTransactionType.QUESTION_REPLY_CREATION, 1);
        POINT_VALUES.put(PointHistory.PointTransactionType.RECEIVED_SHARE_UPVOTE, 3);
        POINT_VALUES.put(PointHistory.PointTransactionType.RECEIVED_QUESTION_COMMENT_UPVOTE, 3);
    }

    private final MemberReader memberReader;
    private final MemberRepository memberRepository;
    private final PointHistoryManager pointHistoryManager;

    @Transactional
    public void processShareArticle(Article.ArticleType articleType, Long memberId, Long targetId) {
        if (articleType != Article.ArticleType.SHARE) return;
        processPointTransaction(
                memberId,
                targetId,
                PointHistory.PointTransactionType.SHARE_ARTICLE_CREATION
        );
    }

    @Transactional
    public void deleteShareArticle(Article article) {
        if (article.getArticleType().equals(Article.ArticleType.QUESTION)) return;
        Long memberId = article.getWriterId();
        Long targetId = article.getId();
        int pointsToSubtract = pointHistoryManager.calculatePointsToSubtract(memberId, targetId);
        processPointTransaction(
                memberId,
                targetId,
                PointHistory.PointTransactionType.SHARE_ARTICLE_DELETION,
                -pointsToSubtract
        );
    }

    @Transactional
    public void processQuestionComment(CommentTarget.TargetType targetType, Long memberId, Long targetId) {
        if (targetType == CommentTarget.TargetType.SHARE_ARTICLE) return;
        PointHistory.PointTransactionType transactionType = targetType == CommentTarget.TargetType.COMMENT
                ? PointHistory.PointTransactionType.QUESTION_COMMENT_CREATION
                : PointHistory.PointTransactionType.QUESTION_REPLY_CREATION;
        processPointTransaction(memberId, targetId, transactionType);
    }

    private void processPointTransaction(Long memberId, Long targetId, PointHistory.PointTransactionType transactionType) {
        processPointTransaction(memberId, targetId, transactionType, POINT_VALUES.get(transactionType));
    }

    private void processPointTransaction(Long memberId, Long targetId, PointHistory.PointTransactionType transactionType, int points) {
        Member member = memberReader.getById(memberId);
        MemberGrade.Grade newGrade = member.getGrade().addValue(points);
        member.updateGrade(newGrade);
        memberRepository.save(member);
        pointHistoryManager.createPointHistory(memberId, points, targetId, transactionType);
    }
}