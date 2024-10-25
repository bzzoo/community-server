package com.app.community.storage.db.command.upvote;

import com.app.community.domain.model.upvote.UpvoteTargetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpvoteJpaRepository extends JpaRepository<UpvoteEntity, Long> {
    boolean existsByExecutorIdAndTargetIdAndTargetType(Long executorId, Long targetId, UpvoteTargetType targetType);
}
