package com.app.community.domain.model.comment;

public record CommentTarget(
        Long targetId,
        CommentTargetType type
) {
}
