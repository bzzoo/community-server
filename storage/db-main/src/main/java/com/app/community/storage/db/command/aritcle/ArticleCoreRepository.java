package com.app.community.storage.db.command.aritcle;

import com.app.community.domain.model.article.Article;
import com.app.community.domain.model.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ArticleCoreRepository implements ArticleRepository {

    private final ArticleJpaRepository articleJpaRepository;
    private final ArticleKeywordJpaRepository articleKeywordJpaRepository;

    @Override
    public Optional<Article> findOneById(Long articleId) {
        return articleJpaRepository.findById(articleId).map(ArticleEntity::toDomain);
    }

    @Override
    public Optional<Article> findById(Long articleId) {
        return articleJpaRepository.findById(articleId)
                .map(articlePs -> {
                    List<ArticleKeywordEntity> articleKeywordsPs = articleKeywordJpaRepository.findByArticleId(articleId);
                    return ArticleEntityConverter.toDomain(articlePs, articleKeywordsPs);
                });
    }

    @Override
    public Article save(Article article) {
        ArticleEntity articleEntity = ArticleEntityConverter.toEntity(article);
        ArticleEntity articlePs = articleJpaRepository.save(articleEntity);

        List<ArticleKeywordEntity> articleKeywordEntities
                = ArticleEntityConverter.toKeywordEntities(article.getTags(), articlePs.getId());
        List<ArticleKeywordEntity> articleKeywordsPs
                = articleKeywordJpaRepository.saveAll(articleKeywordEntities);

        return ArticleEntityConverter.toDomain(articlePs, articleKeywordsPs);
    }

    @Override
    public void updateViewCount(Long articleId, int value) {
        articleJpaRepository.increViewCount(articleId, value);
    }

    @Override
    public void updateCommentCount(Long articleId, int value) {
        articleJpaRepository.incrementCommentCount(articleId, value);
    }

    @Override
    public void updateUpvoteCount(Long articleId, int value) {
        articleJpaRepository.incrementUpvoteCount(articleId, value);
    }
}
