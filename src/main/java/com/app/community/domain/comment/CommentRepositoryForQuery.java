package com.app.community.domain.comment;

import java.util.List;

public interface CommentRepositoryForQuery {
    List<CommentSummary.CommentInfo> findCommentListByArticleId(Long articleId, Long cursor);

    List<CommentSummary.ProfileComment> findAnswerByMember(Long memberId, Long cursor);
}
