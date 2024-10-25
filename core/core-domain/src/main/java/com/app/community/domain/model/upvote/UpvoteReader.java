package com.app.community.domain.model.upvote;

import com.app.community.domain.support.error.DomainException;
import com.app.community.domain.support.error.DomainErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpvoteReader {

    private final UpvoteRepository upvoteRepository;

    public void existsUpvote(Long executorId, UpvoteTarget target) {
        boolean exist = upvoteRepository.findByExecutorIdAndTarget(executorId, target);
        if (exist) throw new DomainException(DomainErrorType.DEFAULT_ERROR);
    }
}
