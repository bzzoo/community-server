package com.app.community.domain.model.article;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Article {
    private final Long id;
    private final Long writerId;
    private ArticleContent content;
    private ArticleType type;
    private ArticleStatus status;
    private ArticleKeywordList tags;

    public Article(
            Long id,
            Long writerId,
            ArticleContent content,
            ArticleType type,
            ArticleStatus status,
            ArticleKeywordList tags
    ) {
        this.id = id;
        this.writerId = writerId;
        this.content = content;
        this.type = type;
        this.status = status;
        this.tags = tags;
    }

    public static Article create(
            Long writerId,
            ArticleContent content,
            ArticleType type,
            ArticleKeywordList tags
    ) {
        return new Article(null, writerId, content, type, ArticleStatus.STEADY, tags);
    }

    public void update(Long memberId, ArticleContent content, ArticleKeywordList tags) {
        validateWriter(memberId);
        this.content = content;
        this.tags = tags;
    }

    public void withdrawal(Long memberId) {
        validateWriter(memberId);
        this.status = ArticleStatus.DELETED;
    }

    public void validateWriter(Long memberId) {
        if (!Objects.equals(this.writerId, memberId)) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}

