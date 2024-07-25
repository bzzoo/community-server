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
}
