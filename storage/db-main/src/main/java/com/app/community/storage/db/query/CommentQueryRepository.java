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
    public List<CommentDetails> findCommentListByArticleId(Long articleId, int size, Long cursor) {
        return commentMapper.findCommentListByArticleId(articleId, size, cursor);
    }

    @Override
    public List<ProfileComment> findAnswerByMember(Long memberId, int size, Long cursor) {
        return commentMapper.findAnswerByMember(memberId, size, cursor);
    }
}