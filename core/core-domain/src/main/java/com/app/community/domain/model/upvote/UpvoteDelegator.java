package com.app.community.domain.model.upvote;

import com.app.community.domain.model.point.PointUpvotedManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpvoteDelegator {

    private final UpvoteReader upvoteReader;
    private final UpvoteWriter upvoteWriter;
    private final PointUpvotedManager pointUpvotedManager;

    public void upvoteArticle(Long executorId, Long writerId, Long articleId) {
        UpvoteTarget upvoteTarget = new UpvoteTarget(articleId, UpvoteTargetType.SHARE_ARTICLE);
        upvoteReader.existsUpvote(executorId, upvoteTarget);
        upvoteWriter.append(executorId, upvoteTarget);
        pointUpvotedManager.handleUpvotedArticle(writerId, articleId);
    }

    public void upvoteComment(Long executorId, Long opponentId, Long commentId) {
        UpvoteTarget upvoteTarget = new UpvoteTarget(commentId, UpvoteTargetType.QUESTION_COMMENT);
        upvoteReader.existsUpvote(executorId, upvoteTarget);
        upvoteWriter.append(executorId, upvoteTarget);
        pointUpvotedManager.handleUpvotedComment(opponentId, commentId);
    }
}

