package com.app.community.domain.model.article;

import com.app.community.domain.model.member.LoginMember;
import com.app.community.domain.model.point.PointContentManager;
import com.app.community.domain.model.upvote.UpvoteDelegator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleReader articleReader;
    private final ArticleWriter articleWriter;
    private final KeywordProcessor keywordProcessor;
    private final ArticleSimpleCacheUpdater articleSimpleCacheUpdater;
    private final ArticleUpdateValidator articleUpdateValidator;
    private final PointContentManager pointContentManager;
    private final UpvoteDelegator upvoteDelegator;

    public Long create(LoginMember member, ArticleContent content, ArticleType type, List<KeywordName> keywordNames) {
        var keywords = keywordProcessor.process(keywordNames);
        var article = articleWriter.append(member, content, type, keywords);
        pointContentManager.handlePosting(article.getWriterId(), article.getId());
        return article.getId();
    }

    public void update(LoginMember member, long articleId, ArticleContent newContent, List<KeywordName> newKeywordNames) {
        var article = articleReader.getById(articleId);
        articleUpdateValidator.validate(article);
        var keywords = keywordProcessor.process(newKeywordNames);
        articleWriter.modify(member, article, newContent, keywords);
    }

    public void delete(LoginMember member, long articleId) {
        var article = articleReader.getById(articleId);
        articleUpdateValidator.validate(article);
        articleWriter.withdraw(member, article);
    }

    public void upvote(Long executorId, Long articleId) {
        var writerId = articleReader.getWriter(articleId);
        upvoteDelegator.upvoteArticle(executorId, writerId, articleId);
        articleSimpleCacheUpdater.increaseUpvoteCnt(articleId);
    }
}
