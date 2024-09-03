package com.app.community.domain.agg.comment;



public record CommentTarget(
        Long targetId,
        CommentTargetType type
) {
}
