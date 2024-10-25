package com.app.community.storage.db.query;

import com.app.community.domain.query.CommentQuery.CommentDetails;
import com.app.community.domain.query.CommentQuery.ProfileComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CommentQueryRepositoryRepository implements com.app.community.domain.query.CommentQueryRepository {

    private final CommentMapper commentMapper;

    @Override
    public List<CommentDetails> findCommentsByArticleId(Long articleId, int size, Long cursor) {
        return commentMapper.findCommentListByArticleId(articleId, size, cursor);
    }

    @Override
    public List<ProfileComment> findAnswerByMember(Long memberId, int size, Long cursor) {
        return commentMapper.findAnswerByMember(memberId, size, cursor);
    }

    @Override
    public List<CommentDetails> findMoreComments(Long articleId, Long parentId, int size, Long cursor, int depth) {
        return commentMapper.findMoreComments(articleId, parentId, size, cursor, depth);
    }

    @Override
    public CommentDetails findById(Long commentId) {
        return commentMapper.findById(commentId);
    }
}