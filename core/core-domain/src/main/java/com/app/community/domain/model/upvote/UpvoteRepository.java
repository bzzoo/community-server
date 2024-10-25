package com.app.community.domain.model.upvote;

public interface UpvoteRepository {
    void save(Upvote upvote);
    boolean findByExecutorIdAndTarget(Long executorId, UpvoteTarget target);
}
