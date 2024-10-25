package com.app.community.api.controller.request;

import com.app.community.domain.model.upvote.UpvoteTarget;
import com.app.community.domain.model.upvote.UpvoteTargetType;

public class UpvoteRequests {

    public record UpvotedRequest(
            Long opponentId,
            Long targetId,
            UpvoteTargetType targetType
    ){
        public UpvoteTarget toTarget(){
            return new UpvoteTarget(targetId, targetType);
        }
    }
}
