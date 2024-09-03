package com.app.community.domain.agg.comment;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long commentId);
    Boolean existsByArticleId(Long articleId);
}
