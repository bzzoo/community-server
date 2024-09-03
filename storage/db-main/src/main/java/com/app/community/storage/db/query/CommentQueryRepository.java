package com.app.community.storage.db.query;

import com.app.community.domain.agg.comment.CommentQuery.*;
import com.app.community.domain.agg.comment.CommentRepositoryForQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class CommentQueryRepository implements CommentRepositoryForQuery {

    private final CommentMapper commentMapper;

    @Override
    public List<CommentInfo> findCommentListByArticleId(Long articleId, Long cursor) {
        return commentMapper.findCommentListByArticleId(articleId, cursor);
    }

    @Override
    public List<ProfileComment> findAnswerByMember(Long memberId, Long cursor) {
        return commentMapper.findAnswerByMember(memberId, cursor);
    }
}