package com.app.community.storage.aritcle;

import com.app.community.domain.aritcle.Article;
import com.app.community.domain.aritcle.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private final ArticleJpaRepository articleJpaRepository;
    private final ArticleKeywordJpaRepository articleKeywordJpaRepository;

    @Override
    public Optional<Article> findById(Long articleId) {
        return articleJpaRepository.findById(articleId).map(ArticleEntity::toDomain);
    }

    @Override
    public void save(Article article) {
        List<ArticleKeywordEntity> articleKeywordEntityList = article.getKeywordList().stream()
                .map(keyword -> ArticleKeywordEntity.fromDomain(keyword, article))
                .toList();
        articleJpaRepository.save(ArticleEntity.fromDomain(article)).toDomain();
        articleKeywordJpaRepository.saveAll(articleKeywordEntityList);
    }

    @Override
    public void update(Article article) {
        articleKeywordJpaRepository.deleteByArticleId(article.getId());
        List<ArticleKeywordEntity> articleKeywordEntityList = article.getKeywordList().stream()
                .map(keyword -> ArticleKeywordEntity.fromDomain(keyword, article))
                .toList();
        articleJpaRepository.save(ArticleEntity.fromDomain(article)).toDomain();
        articleKeywordJpaRepository.saveAll(articleKeywordEntityList);
    }
}
