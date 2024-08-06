package com.app.community.storage.upvote;

import com.app.community.domain.upvote.Upvote;
import com.app.community.domain.upvote.UpvoteRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UpvoteRepositoryImpl implements UpvoteRepository {

    private final UpvoteJpaRepository upvoteJpaRepository;

    @Override
    public void save(Upvote upvote) {
        upvoteJpaRepository.save(UpvoteEntity.fromDomain(upvote));
    }

    @Override
    public boolean findByExecutorIdAndTarget(
            @NotNull Long executorId,
            @NotNull Long targetId,
            Upvote.@NotNull TargetType targetType
    ) {
        return upvoteJpaRepository.existsByExecutorIdAndTargetIdAndTargetType(executorId, targetId, targetType);
    }
}
