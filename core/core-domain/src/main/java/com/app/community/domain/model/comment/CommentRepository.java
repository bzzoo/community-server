package com.app.community.domain.model.comment;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long commentId);
    Boolean existsByArticleId(Long articleId);


    /* 단순 업데이트 */
    void updateUpvoteCount(Long commentId, int value);
}
