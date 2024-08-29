package com.app.community.controller.request;

import com.app.community.domain.agg.comment.CommentTarget;
import com.app.community.domain.agg.comment.CommentTargetType;

public class CommentRequests {

    public record NewCommentRequest(
            Long articleId,
            String content,
            Long targetId,
            CommentTargetType targetType
    ){
        public CommentTarget toTarget(){
            return new CommentTarget(targetId, targetType);
        }
    }

    public record UpdateRequest(
            String content,
            CommentTargetType targetType
    ){

    }
}
