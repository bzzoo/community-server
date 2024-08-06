package com.app.community.domain.upvote;

import com.app.community.domain.member.MemberPointManager;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpvoteService {

    private final UpvoteReader upvoteReader;
    private final UpvoteAppender upvoteAppender;
    private final MemberPointManager memberPointManager;

    public void upvote(
            @NotNull Long executorId,
            @NotNull Long opponentId,
            @NotNull Long targetId,
            Upvote.@NotNull TargetType targetType
    ){
        upvoteReader.existsUpvote(executorId, targetId, targetType);
        upvoteAppender.append(executorId, targetId, targetType);
        memberPointManager.addPointForUpvote(opponentId, targetId, targetType);
    }
}

