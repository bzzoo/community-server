package com.app.community.storage.aritcle;

import com.app.community.domain.aritcle.Article;
import com.app.community.domain.aritcle.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    @Override
    public Optional<Article> findById(Long articleId) {
        return Optional.empty();
    }

    @Override
    public Article save(Article article) {
        return null;
    }
}
