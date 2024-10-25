package com.app.community.domain.query;

import com.app.community.domain.model.article.ArticleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleQuery {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleDetails {
        private Long id;
        private ArticleContentInfo contents;
        private ArticleAuthor author;
        private ArticleType type;
        private List<ArticleKeywordInfo> keywords;
        private LocalDateTime updatedAt;
        private LocalDateTime createdAt;
        private Integer viewCount;
        private Integer commentCount;
        private Integer upvoteCount;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleSummary {
        private Long id;
        private ArticleContentInfo contents;
        private ArticleType type;
        private ArticleAuthor author;
        private List<ArticleKeywordInfo> keywords;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Integer viewCount;
        private Integer commentCount;
        private Integer upvoteCount;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleActivity {
        private Long id;
        private ArticleContentInfo contents;
        private ArticleType type;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleContentInfo {
        private String title;
        private String body;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleKeywordInfo {
        private Long id;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleAuthor {
        private Long id;
        private String nickname;
        private String profileImagePath;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
