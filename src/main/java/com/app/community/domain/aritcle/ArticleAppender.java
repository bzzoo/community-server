package com.app.community.domain.aritcle;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ArticleAppender {

    private final KeywordGenerator keywordGenerator;
    private final ArticleRepository articleRepository;

    public Article append(
            @NotNull Long memberId,
            @NotNull String title,
            @NotNull String content,
            Article.@NotNull ArticleType articleType,
            List<String> keywordNameList
    ) {
        Article article = Article.create(memberId, title, content, articleType);
        List<Keyword> keywordList = keywordGenerator.saveIfNotExists(keywordNameList);
        article.addNewKeywordList(keywordList);
        return articleRepository.save(article);
    }

    public void update(
            @NotNull Long memberId,
            Article article,
            String title,
            String content,
            List<String> newKeywordNameList) {
        article.validateOwner(memberId);
        article.updateContent(title, content);
        articleRepository.save(article);
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
