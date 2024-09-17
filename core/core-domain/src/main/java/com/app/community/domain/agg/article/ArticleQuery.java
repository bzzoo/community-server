package com.app.community.domain.agg.article;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleQuery {

    @Getter
    @AllArgsConstructor
    public static class ArticleDetails {
        private final Long articleId;
        private final String title;
        private final String content;
        private final ArticleType articleType;
        private final Author author;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
        private final List<ArticleKeywordInfo> keywordList;
    }

    @Getter
    @AllArgsConstructor
    public static class ArticleInfo {
        private final Long articleId;
        private final String title;
        private final String content;
        private final ArticleType articleType;
        private final Author author;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
        private final List<ArticleKeywordInfo> keywordList;
    }

    @Getter
    @AllArgsConstructor
    public static class Author {
        private final Long memberId;
        private final String nickname;
        private final String profileImagePath;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
    }

    @Getter
    @AllArgsConstructor
    public static class ArticleKeywordInfo {
        private final Long keywordId;
        private final String keywordName;
    }

    @Getter
    @AllArgsConstructor
    public static class ArticleActivity {
        private final Long id;
        private final Long articleId;
        private final String title;
        private final String content;
        private final ArticleType articleType;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
    }
}
