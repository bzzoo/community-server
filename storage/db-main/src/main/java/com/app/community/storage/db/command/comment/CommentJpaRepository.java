package com.app.community.storage.db.command.comment;

import com.app.community.domain.agg.comment.CommentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

    boolean existsByArticleIdAndStatus(Long targetId, CommentStatus status);

    @Modifying
    @Transactional
    @Query("UPDATE CommentEntity c SET c.upvoteCount = c.upvoteCount + :value WHERE c.id = :commentId")
    void incrementUpvote(Long commentId, int value);
}
