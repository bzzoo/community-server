package com.app.community.domain.agg.upvote;

public interface UpvoteRepository {
    void save(Upvote upvote);
    boolean findByExecutorIdAndTarget(Long executorId, UpvoteTarget target);
}
