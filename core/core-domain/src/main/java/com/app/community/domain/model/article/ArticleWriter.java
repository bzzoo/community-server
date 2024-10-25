package com.app.community.domain.model.article;

import com.app.community.domain.model.member.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ArticleWriter {

    private final ArticleRepository articleRepository;

    public Article append(LoginMember member, ArticleContent content, ArticleType type, List<Keyword> keywords) {
        ArticleKeywordList articleKeywordList = createArticleKeywordList(keywords);
        Article newArticle = Article.create(member.memberId(), content, type, articleKeywordList);
        return articleRepository.save(newArticle);
    }

    public void modify(LoginMember member, Article article, ArticleContent newContent, List<Keyword> keywords) {
        ArticleKeywordList articleKeywordList = createArticleKeywordList(keywords);
        article.update(member.memberId(), newContent, articleKeywordList);
        articleRepository.save(article);
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
