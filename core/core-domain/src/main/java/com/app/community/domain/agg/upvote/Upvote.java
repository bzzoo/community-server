package com.app.community.domain.agg.upvote;

import lombok.Getter;

@Getter
public class Upvote {

    private final Long id;
    private final Long executorId;
    private UpvoteTarget upvoteTarget;

    public Upvote(
            Long id,
            Long executorId,
            UpvoteTarget upvoteTarget
    ) {
        this.id = id;
        this.executorId = executorId;
        this.upvoteTarget = upvoteTarget;
    }

    public static Upvote create(
             Long executorId,
             UpvoteTarget upvoteTarget
    ) {
        return new Upvote(null, executorId, upvoteTarget);
    }
}
