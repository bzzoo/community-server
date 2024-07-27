package com.app.community.domain.aritcle;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleSummary {

    public record ArticleDetails(
            Long articleId,
            String title,
            String content,
            Article.ArticleType articleType,
            Author author,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<ArticleKeywordInfo> keywordList
    ) {
    }

    public record ArticleInfo(
            Long articleId,
            String title,
            String content,
            Article.ArticleType articleType,
            Author author,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<ArticleKeywordInfo> keywordList
    ) {
    }

    public record Author(
            Long memberId,
            String nickname,
            String profileImagePath,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }

    public record ArticleKeywordInfo(
            Long keywordId,
            String keywordName
    ) {
    }

    public record ArticleActivity(
            Long articleId,
            String title,
            String content,
            Article.ArticleType articleType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){
    }
}
