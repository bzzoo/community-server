package com.app.community.domain.agg.article;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Article {
    private final Long id;
    private final Long writerId;
    private ArticleContent content;
    private ArticleStatus status;
    private ArticleKeywordList tags;

    public Article(
            Long id,
            Long writerId,
            ArticleContent content,
            ArticleStatus status,
            ArticleKeywordList tags
    ) {
        this.id = id;
        this.writerId = writerId;
        this.content = content;
        this.status = status;
        this.tags = tags;
    }

    public static Article create(
            Long writerId,
            ArticleContent content,
            ArticleKeywordList tags
    ) {
        return new Article(null, writerId, content , ArticleStatus.STEADY, tags);
    }

    public void update(Long memberId, ArticleContent content, ArticleKeywordList tags){
        validateWriter(memberId);
        this.content = content;
        this.tags = tags;
    }

    public void withdrawal(Long memberId){
        validateWriter(memberId);
        this.status = ArticleStatus.DELETED;
    }

    public void validateWriter(Long memberId){
        if(!Objects.equals(this.writerId, memberId)){
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}

