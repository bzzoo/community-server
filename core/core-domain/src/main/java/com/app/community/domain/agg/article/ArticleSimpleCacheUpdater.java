package com.app.community.domain.agg.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ArticleSimpleCacheUpdater {

    private final ArticleRepository articleRepository;

    public void increaseViewCnt(Long articleId) {
        articleRepository.updateViewCount(articleId, 1);
    }

    public void increaseCommentCnt(Long articleId) {
        articleRepository.updateViewCount(articleId, 1);
    }

    public void increaseUpvoteCnt(Long articleId) {
        articleRepository.updateViewCount(articleId, 1);
    }
}
