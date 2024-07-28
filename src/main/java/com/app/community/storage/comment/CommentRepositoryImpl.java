package com.app.community.storage.comment;

import com.app.community.domain.comment.Comment;
import com.app.community.domain.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public Comment save(Comment comment) {
        return commentJpaRepository.save(CommentEntity.fromDomain(comment)).toDomain();
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return Optional.empty();
    }

    @Override
    public boolean existsByArticleId(Long articleId) {
        return commentJpaRepository.existsByArticleIdAndIsDeleteFalse(articleId);
    }
}
