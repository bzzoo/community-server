package com.app.community.domain.agg.upvote;

import com.app.community.domain.agg.point.PointProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpvoteService {

    private final UpvoteReader upvoteReader;
    private final UpvoteWriter upvoteWriter;
    private final PointProcessor pointProcessor;

    public void upvote(Long executorId, Long opponentId, UpvoteTarget target) {
        upvoteReader.existsUpvote(executorId, target);
        upvoteWriter.append(executorId, target);
        //pointHistoryProcessor.addPointForUpvote(opponentId, target);
    }
}

