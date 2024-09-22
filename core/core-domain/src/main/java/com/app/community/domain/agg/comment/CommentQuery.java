package com.app.community.domain.agg.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class CommentQuery {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentDetails {
        private Long id;
        private Long articleId;
        private Long parentId;
        private String body;
        private CommentStatus status;
        private CommentAuthor author;
        private List<CommentDetails> children;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Integer upvoteCount;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentAuthor {
        private Long id;
        private String nickname;
        private String profileImagePath;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProfileComment {
        private Long commentId;
        private Long articleId;
        private String articleTitle;
        private String commentBody;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
