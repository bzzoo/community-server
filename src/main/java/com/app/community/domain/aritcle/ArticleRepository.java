package com.app.community.domain.aritcle;

import java.util.Optional;

public interface ArticleRepository {
    Optional<Article> findById(Long articleId);
    Article save(Article article);
    void update(Article article);
    void delete(Article article);
}
