package com.app.community.domain.aritcle;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ArticleAppender {

    private final ArticleRepository articleRepository;

    public void append(
            @NotNull Long memberId,
            @NotNull String title,
            @NotNull String content,
            Article.@NotNull ArticleType articleType,
            List<Keyword> keywordList
    ) {
        Article article = Article.create(memberId, title, content, articleType, keywordList);
        articleRepository.save(article);
    }

    public void update(
            @NotNull Long memberId,
            Article article,
            String title,
            String content,
            List<Keyword> newKeywordList
    ) {
        article.validateOwner(memberId);
        article.updateContent(title, content, newKeywordList);
        articleRepository.update(article);
    }

    public void delete(
            @NotNull Long memberId,
            Article article
    ) {
        article.validateOwner(memberId);
        article.delete();
        articleRepository.save(article);
    }
}
