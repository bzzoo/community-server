package com.app.community.storage.db.command.comment;


import com.app.community.domain.agg.comment.Comment;
import com.app.community.domain.agg.comment.CommentRepository;
import com.app.community.domain.agg.comment.CommentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class CommentCoreRepository implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public Comment save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(
                comment.getId(),
                comment.getArticleId(),
                comment.getWriterId(),
                comment.getBody(),
                comment.getTarget().targetId(),
                comment.getTarget().type(),
                comment.getStatus()
        );
        return commentJpaRepository.save(commentEntity).toDomain();
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return commentJpaRepository.findById(commentId).map(CommentEntity::toDomain);
    }

    @Override
    public Boolean existsByArticleId(Long articleId) {
        return commentJpaRepository.existsByArticleIdAndStatus(articleId, CommentStatus.STEADY);
    }
}
