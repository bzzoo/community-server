package com.app.community.domain.model.upvote;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpvoteWriter {

    private final UpvoteRepository upvoteRepository;

    public void append(Long executorId, UpvoteTarget target) {
        Upvote upvote = Upvote.create(executorId, target);
        upvoteRepository.save(upvote);
    }
}
