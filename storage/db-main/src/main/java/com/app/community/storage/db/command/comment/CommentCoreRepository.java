package com.app.community.storage.db.command.comment;

import com.app.community.domain.model.comment.Comment;
import com.app.community.domain.model.comment.CommentRepository;
import com.app.community.domain.model.comment.CommentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentCoreRepository implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public Comment save(Comment comment) {
        return commentJpaRepository.save(CommentEntity.fromDomain(comment)).toDomain();
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return commentJpaRepository.findById(commentId).map(CommentEntity::toDomain);
    }

    @Override
    public Boolean existsByArticleId(Long articleId) {
        return commentJpaRepository.existsByArticleIdAndStatus(articleId, CommentStatus.STEADY);
    }

    @Override
    public void updateUpvoteCount(Long commentId, int value) {
        commentJpaRepository.incrementUpvote(commentId, value);
    }
}
