package com.app.community.api.upvote;

import com.app.community.domain.upvote.Upvote;
import org.jetbrains.annotations.NotNull;

public class UpvoteRequest {

    public record UpvotedRequest(
            Long opponentId,
            Long targetId,
            Upvote.TargetType targetType
    ){
    }
}
