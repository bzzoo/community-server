package com.app.community.domain.comment;

public record CommentTarget(
        Long targetId,
        TargetType targetType
) {
    public enum TargetType {
        SHARE_ARTICLE, QUESTION_ARTICLE, COMMENT, REPLY
    }
}
