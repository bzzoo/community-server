package com.app.community.domain.agg.comment;

import com.app.community.domain.agg.comment.CommentQuery.CommentInfo;
import com.app.community.domain.agg.comment.CommentQuery.ProfileComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CommentReadService {

    private final CommentRepositoryForQuery commentRepositoryForQuery;

    public List<CommentInfo> getCommentList(Long articleId, int size, Long cursor){
        return commentRepositoryForQuery.findCommentListByArticleId(articleId, size, cursor);
    }

    public List<ProfileComment> getAnswerByMember(Long loginMemberId, int size, Long cursor) {
        return commentRepositoryForQuery.findAnswerByMember(loginMemberId, size, cursor);
    }
}
