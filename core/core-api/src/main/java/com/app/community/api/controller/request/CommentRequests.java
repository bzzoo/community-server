package com.app.community.api.controller.request;

import com.app.community.domain.model.comment.CommentTarget;
import com.app.community.domain.model.comment.CommentTargetType;

public class CommentRequests {

    public record NewCommentRequest(
            Long articleId,
            String body,
            Long targetId,
            String targetType
    ){
        public CommentTarget toTarget(){
            CommentTargetType targetType = CommentTargetType.from(this.targetType);
            return new CommentTarget(targetId, targetType);
        }
    }

    public record UpdateRequest(
            String body
    ){

    }
}
