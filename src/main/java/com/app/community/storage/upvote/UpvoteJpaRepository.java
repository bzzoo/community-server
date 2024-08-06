package com.app.community.storage.upvote;

import com.app.community.domain.upvote.Upvote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpvoteJpaRepository extends JpaRepository<UpvoteEntity, Long> {
    boolean existsByExecutorIdAndTargetIdAndTargetType(Long executorId, Long targetId, Upvote.TargetType targetId1);
}
