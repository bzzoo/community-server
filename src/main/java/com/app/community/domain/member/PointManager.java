package com.app.community.domain.member;

import com.app.community.domain.aritcle.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PointManager {

    private static final Integer SHARE_ARTICLE_CREATE_POINT = 10;
    private static final Integer UPVOTED_ARTICLE_POINT = 3;
    private static final Integer UPVOTED_COMMENT_POINT = 3;

    private final MemberReader memberReader;
    private final MemberRepository memberRepository;
    private final PointHistoryRepository historyRepository;

    public void processShareArticle(Article.ArticleType articleType, Long memberId, Long targetId) {
        if (articleType != Article.ArticleType.SHARE) return;
        Member member = memberReader.getById(memberId);
        MemberGrade.Grade grade = member.getGrade();
        MemberGrade.Grade newGrade = grade.addValue(SHARE_ARTICLE_CREATE_POINT);
        member.updateGrade(newGrade);
        memberRepository.save(member);

        PointHistory pointHistory = PointHistory.create(memberId,
                SHARE_ARTICLE_CREATE_POINT,
                targetId,
                PointHistory.PointTransactionType.SHARE_ARTICLE_CREATION
        );
        historyRepository.save(pointHistory);
    }

    public void deleteIfShareArticle(Article article) {
        if(article.getArticleType().equals(Article.ArticleType.QUESTION)) return;
        Member member = memberReader.getById(article.getWriterId());
        List<PointHistory> historyList =
                historyRepository.findByMemberIdAndTargetId(member.getId(), article.getId());
        int minusPoint = historyList.stream()
                .mapToInt(PointHistory::getValue)
                .sum();

        MemberGrade.Grade grade = member.getGrade();
        MemberGrade.Grade newGrade = grade.subtractScore(SHARE_ARTICLE_CREATE_POINT);
        member.updateGrade(newGrade);
        memberRepository.save(member);
        PointHistory pointHistory = PointHistory.create(
                member.getId(),
                minusPoint,
                article.getId(),
                PointHistory.PointTransactionType.SHARE_ARTICLE_DELETION);
        historyRepository.save(pointHistory);
    }
}
