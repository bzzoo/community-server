package com.app.community.domain.model.comment;

import com.app.community.domain.model.member.LoginMember;
import com.app.community.domain.model.article.ArticleSimpleCacheUpdater;
import com.app.community.domain.model.point.PointContentManager;
import com.app.community.domain.model.upvote.UpvoteDelegator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentWriter commentWriter;
    private final CommentReader commentReader;
    private final PointContentManager pointContentManager;
    private final ArticleSimpleCacheUpdater articleSimpleCacheUpdater;
    private final UpvoteDelegator upvoteDelegator;

    public void create(LoginMember member, Long articleId, CommentTarget target, String body) {
        var comment = commentWriter.append(member.memberId(), articleId, target, body);
        pointContentManager.handleCommenting(member.memberId(), comment.getId());
        articleSimpleCacheUpdater.increaseCommentCnt(articleId);
    }

    public void update(LoginMember member, Long commentId, String content) {
        Comment comment = commentReader.getById(commentId);
        commentWriter.update(member, comment, content);
    }

    public void delete(LoginMember member, Long commentId) {
        Comment comment = commentReader.getById(commentId);
        commentWriter.delete(member, comment);
        articleSimpleCacheUpdater.decreaseCommentCnt(comment.getArticleId());
    }

    public void upvote(Long executorId, Long commentId) {
        var writerId = commentReader.getWriterId(commentId);
        upvoteDelegator.upvoteComment(executorId, writerId, commentId);
        commentWriter.simpleCountUpdate(commentId);
    }
}
