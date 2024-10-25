package com.app.community.domain.query;

import com.app.community.domain.query.CommentQuery.CommentDetails;
import com.app.community.domain.query.CommentQuery.ProfileComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentQueryService {

    private final CommentQueryRepository commentQueryRepository;

    public List<CommentDetails> getCommentsByArticleId(Long articleId, int size, Long cursor) {
        return commentQueryRepository.findCommentsByArticleId(articleId, size, cursor);
    }

    public List<CommentDetails> getCommentsWithDepth(Long articleId, Long parentId, int size, Long cursor, int depth) {
        return commentQueryRepository.findMoreComments(articleId, parentId, size, cursor, depth);
    }

    public List<ProfileComment> getAnswerByMember(Long loginMemberId, int size, Long cursor) {
        return commentQueryRepository.findAnswerByMember(loginMemberId, size, cursor);
    }

    public CommentDetails getComment(Long commentId) {
        return commentQueryRepository.findById(commentId);
    }
}
