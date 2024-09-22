package com.app.community.domain.agg.article;

import java.util.Optional;

public interface ArticleRepository {
    Optional<Article> findById(Long articleId);
    Article save(Article article);

    /* 단순 수정, 변경 */ //TODO 분리
    void updateViewCount(Long articleId, int value);
    void updateCommentCount(Long articleId, int value);
    void updateUpvoteCount(Long articleId, int value);
}
