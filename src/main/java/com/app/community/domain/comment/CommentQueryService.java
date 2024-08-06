package com.app.community.domain.comment;

import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CommentQueryService {

    private final CommentRepositoryForQuery commentRepositoryForQuery;

    public CursorResult<CommentSummary.CommentInfo> getCommentList(@NotNull Long articleId, @NotNull Long cursor){
        List<CommentSummary.CommentInfo> commentList = commentRepositoryForQuery.findCommentListByArticleId(articleId, cursor);
        return CursorResult.of(commentList, 41, CommentSummary.CommentInfo::commentId);
    }

    public CursorResult<CommentSummary.ProfileComment> getAnswerByMember(Long loginMemberId, Long cursor) {
        List<CommentSummary.ProfileComment> answerByMember = commentRepositoryForQuery.findAnswerByMember(loginMemberId, cursor);
        return CursorResult.of(answerByMember, 20, CommentSummary.ProfileComment::commentId);
    }
}
