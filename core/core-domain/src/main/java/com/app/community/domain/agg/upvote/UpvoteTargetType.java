package com.app.community.domain.agg.upvote;

import com.app.community.domain.agg.point.PointEventType;
import lombok.Getter;

@Getter
public enum UpvoteTargetType {
    SHARE_ARTICLE(PointEventType.RECEIVED_SHARE_UPVOTE),
    QUESTION_COMMENT(PointEventType.RECEIVED_QUESTION_COMMENT_UPVOTE);

    private final PointEventType eventType;

    UpvoteTargetType(PointEventType eventType) {
        this.eventType = eventType;
    }

}
