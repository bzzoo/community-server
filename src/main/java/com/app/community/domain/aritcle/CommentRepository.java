package com.app.community.domain.aritcle;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long commentId);

}
