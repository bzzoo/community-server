package com.app.community.domain.upvote;

import org.jetbrains.annotations.NotNull;

public interface UpvoteRepository {
    void save(Upvote upvote);
    boolean findByExecutorIdAndTarget(@NotNull Long executorId, @NotNull Long targetId, Upvote.@NotNull TargetType targetType);
}
