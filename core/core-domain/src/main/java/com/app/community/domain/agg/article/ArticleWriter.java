package com.app.community.domain.agg.article;

import com.app.community.domain.agg.member.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ArticleWriter {

    private final ArticleRepository articleRepository;

    public void modify(LoginMember member, Article article, ArticleContent newContent, List<Keyword> keywords) {
        ArticleKeywordList articleKeywordList = createArticleKeywordList(keywords);
        article.update(member.memberId(), newContent, articleKeywordList);
        articleRepository.save(article);
    }

    public Article append(LoginMember member, ArticleContent content, List<Keyword> keywords) {
        ArticleKeywordList articleKeywordList = createArticleKeywordList(keywords);
        Article newArticle = Article.create(member.memberId(), content, articleKeywordList);
        return articleRepository.save(newArticle);
    }

    public void withdraw(LoginMember member, Article article) {
        article.withdrawal(member.memberId());
        articleRepository.save(article);
    }

    private static ArticleKeywordList createArticleKeywordList(List<Keyword> keywords) {
        return new ArticleKeywordList(
                keywords.stream().map(Keyword::getId).collect(Collectors.toSet())
        );
    }
}
