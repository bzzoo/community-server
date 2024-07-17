package com.app.community.domain.aritcle;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleReader articleReader;
    private final ArticleAppender articleAppender;

    public void create(
            @NotNull Long memberId,
            @NotNull String title,
            @NotNull String content,
            Article.@NotNull ArticleType articleType,
            List<String> keywordNameList
    ) {
        articleAppender.append(memberId, title, content, articleType, keywordNameList);
    }

    @Transactional
    public void update(
            @NotNull Long memberId,
            @NotNull Long articleId,
            @NotNull String title,
            @NotNull String content,
            List<String> newKeywordNameList
    ) {
        //TODO 수정 가능 유효성 추가
        var article = articleReader.getById(articleId);
        articleAppender.update(memberId, article, title, content, newKeywordNameList);
    }

    @Transactional
    public void delete(
            @NotNull Long memberId,
            @NotNull Long articleId
    ) {
        //TODO 삭제 가능 유혀성 추가
        var article = articleReader.getById(articleId);
        articleAppender.delete(memberId, article);
    }
}
