package com.app.community.domain.agg.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class CommentQuery {

    @Getter
    @AllArgsConstructor
    public static class CommentInfo {
        private Long commentId;
        private Long articleId;
        private Long parentId;
        private String content;
        private CommentStatus status;
        private Author author;
        private List<CommentInfo> childCommentList;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @AllArgsConstructor
    public static class Author {
        private Long memberId;
        private String nickname;
        private String profileImagePath;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

    }

    @Getter
    @AllArgsConstructor
    public static class ProfileComment {
        private Long commentId;
        private Long articleId;
        private String articleTitle;
        private String content;
        private CommentStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
