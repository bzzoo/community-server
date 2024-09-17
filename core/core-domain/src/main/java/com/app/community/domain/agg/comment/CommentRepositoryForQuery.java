package com.app.community.domain.agg.comment;

import com.app.community.domain.agg.comment.CommentQuery.*;

import java.util.List;

public interface CommentRepositoryForQuery {
    List<CommentInfo> findCommentListByArticleId(Long articleId, int size, Long cursor);

    List<ProfileComment> findAnswerByMember(Long memberId, int size, Long cursor);
}
