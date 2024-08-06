package com.app.community.domain.upvote;

import com.app.community.support.error.CoreApiException;
import com.app.community.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpvoteReader {

    private final UpvoteRepository upvoteRepository;

    public void existsUpvote(
            @NotNull Long executorId,
            @NotNull Long targetId,
            Upvote.@NotNull TargetType targetType
    ) {
        boolean exist = upvoteRepository.findByExecutorIdAndTarget(executorId, targetId, targetType);
        if(exist) throw new CoreApiException(ErrorType.DEFAULT_ERROR);
    }
}
