package com.app.community.domain.query;

import java.util.List;

public interface CommentQueryRepository {
    List<CommentQuery.CommentDetails> findCommentsByArticleId(Long articleId, int size, Long cursor);

    List<CommentQuery.ProfileComment> findAnswerByMember(Long memberId, int size, Long cursor);

    List<CommentQuery.CommentDetails> findMoreComments(Long articleId, Long parentId, int size, Long cursor, int depth);

    CommentQuery.CommentDetails findById(Long commentId);
}
