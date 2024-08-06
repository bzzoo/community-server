package com.app.community.api.comment;

import com.app.community.domain.comment.CommentTarget;

public class CommentRequest {

    public record CreateRequest(
            Long targetId,
            String content,
            CommentTarget.TargetType targetType
    ){}

    public record UpdateRequest(
            String content,
            CommentTarget.TargetType targetType
    ){}
}
