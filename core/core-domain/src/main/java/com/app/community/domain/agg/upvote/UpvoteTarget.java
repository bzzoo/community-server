package com.app.community.domain.agg.upvote;

public record UpvoteTarget(
        Long targetId,
        UpvoteTargetType targetType
) {
}
