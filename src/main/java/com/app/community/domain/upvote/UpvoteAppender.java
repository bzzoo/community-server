package com.app.community.domain.upvote;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpvoteAppender {

    private final UpvoteRepository upvoteRepository;

    public void append(
            @NotNull Long executorId,
            @NotNull Long targetId,
            Upvote.@NotNull TargetType targetType
    ) {
        Upvote upvote = Upvote.create(executorId, targetId, targetType);
        upvoteRepository.save(upvote);
    }
}
