package com.app.community.domain.model.upvote;

public record UpvoteTarget(
        Long targetId,
        UpvoteTargetType targetType
) {
}
