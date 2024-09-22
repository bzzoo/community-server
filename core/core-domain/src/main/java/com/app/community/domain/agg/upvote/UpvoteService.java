package com.app.community.domain.agg.upvote;

import com.app.community.domain.agg.article.ArticleSimpleCacheUpdater;
import com.app.community.domain.agg.point.PointProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpvoteService {

    private final UpvoteReader upvoteReader;
    private final UpvoteWriter upvoteWriter;
    private final PointProcessor pointProcessor;
    private final ArticleSimpleCacheUpdater articleSimpleCacheUpdater;

    public void upvote(Long executorId, Long opponentId, UpvoteTarget target) {
        upvoteReader.existsUpvote(executorId, target);
        upvoteWriter.append(executorId, target);
        //TODO 타입에 대해서 실행하는 로직 추가 - 이벤트 레이즈
        articleSimpleCacheUpdater.increaseUpvoteCnt(target.targetId());
        //pointHistoryProcessor.addPointForUpvote(opponentId, target);
    }
}

