package com.app.community.domain.agg.article;

import com.app.community.domain.agg.member.LoginMember;
import com.app.community.domain.agg.point.PointProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleReader articleReader;
    private final ArticleWriter articleWriter;
    private final KeywordProcessor keywordProcessor;
    private final PointProcessor pointProcessor;
    private final ArticleUpdateValidator articleUpdateValidator;

    public void create(LoginMember member, ArticleContent content, List<KeywordName> keywordNames) {
        var keywords = keywordProcessor.process(keywordNames);
        var article = articleWriter.append(member, content, keywords);
        pointProcessor.rewardPosting(article.getWriterId(), article.getId());
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
}
