package com.app.community.domain.model.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ArticleSimpleCacheUpdater {

    public static final int DEFAULT_ACCESS_COUNT = 1;

    private final ArticleRepository articleRepository;

    public void increaseViewCnt(Long articleId) {
        articleRepository.updateViewCount(articleId, DEFAULT_ACCESS_COUNT);
    }

    public void increaseCommentCnt(Long articleId) {
        articleRepository.updateCommentCount(articleId, DEFAULT_ACCESS_COUNT);
    }

    public void increaseUpvoteCnt(Long articleId) {
        articleRepository.updateUpvoteCount(articleId, DEFAULT_ACCESS_COUNT);
    }

    public void decreaseCommentCnt(Long articleId) {
        articleRepository.updateCommentCount(articleId, -DEFAULT_ACCESS_COUNT);
    }
}
