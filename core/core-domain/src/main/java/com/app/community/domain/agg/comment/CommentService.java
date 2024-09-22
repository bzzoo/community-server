package com.app.community.domain.agg.comment;

import com.app.community.domain.agg.article.ArticleSimpleCacheUpdater;
import com.app.community.domain.agg.member.LoginMember;
import com.app.community.domain.agg.point.PointProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentWriter commentWriter;
    private final CommentReader commentReader;
    private final PointProcessor pointProcessor;
    private final ArticleSimpleCacheUpdater articleSimpleCacheUpdater;

    public void create(LoginMember member, Long articleId, CommentTarget target, String body) {
        var comment = commentWriter.append(member.memberId(), articleId, target, body);
        pointProcessor.rewardCommenting(member.memberId(), comment.getId());
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
}
