package com.app.community.domain.agg.article;

import java.util.Optional;

public interface ArticleRepository {
    Optional<Article> findById(Long articleId);
    Article save(Article article);
}
