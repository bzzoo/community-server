package com.app.community.controller.request;

import com.app.community.domain.agg.upvote.UpvoteTarget;
import com.app.community.domain.agg.upvote.UpvoteTargetType;

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
