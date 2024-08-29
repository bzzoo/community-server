package com.app.community.storage.db.command.comment;

import com.app.community.domain.agg.comment.CommentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

    boolean existsByArticleIdAndStatus(Long targetId, CommentStatus status);
}
