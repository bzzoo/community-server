package com.app.community.domain.agg.comment;


import com.app.community.domain.agg.point.PointEventType;

import java.util.function.Function;

public enum CommentTargetType {
    SHARE(ignored -> null),
    COMMENT(ignored -> PointEventType.QUESTION_REPLY_CREATION),
    QUESTION(ignored -> PointEventType.QUESTION_COMMENT_CREATION);

    private final Function<Void, PointEventType> eventTypeMapper;

    CommentTargetType(Function<Void, PointEventType> eventTypeMapper) {
        this.eventTypeMapper = eventTypeMapper;
    }

    public PointEventType getEventType() {
        return eventTypeMapper.apply(null);
    }
}