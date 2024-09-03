package com.app.community.storage.db.command.upvote;

import com.app.community.domain.agg.upvote.Upvote;
import com.app.community.domain.agg.upvote.UpvoteRepository;
import com.app.community.domain.agg.upvote.UpvoteTarget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UpvoteCoreRepository implements UpvoteRepository {

    private final UpvoteJpaRepository upvoteJpaRepository;

    @Override
    public void save(Upvote upvote) {
        UpvoteEntity upvoteEntity = new UpvoteEntity(
                upvote.getId(),
                upvote.getExecutorId(),
                upvote.getUpvoteTarget().targetId(),
                upvote.getUpvoteTarget().targetType()
        );
        upvoteJpaRepository.save(upvoteEntity);
    }

    @Override
    public boolean findByExecutorIdAndTarget(Long executorId, UpvoteTarget target) {
        return upvoteJpaRepository.existsByExecutorIdAndTargetIdAndTargetType(executorId, target.targetId(), target.targetType());
    }
}
