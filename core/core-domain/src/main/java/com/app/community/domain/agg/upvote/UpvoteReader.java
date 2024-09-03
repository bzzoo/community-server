package com.app.community.domain.agg.upvote;

import com.app.community.domain.support.error.CoreApiException;
import com.app.community.domain.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpvoteReader {

    private final UpvoteRepository upvoteRepository;

    public void existsUpvote(Long executorId, UpvoteTarget target) {
        boolean exist = upvoteRepository.findByExecutorIdAndTarget(executorId, target);
        if (exist) throw new CoreApiException(ErrorType.DEFAULT_ERROR);
    }
}
