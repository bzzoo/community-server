package com.app.community.domain.aritcle;

import java.util.List;

public interface ArticleRepositoryForQuery {
    List<ArticleSummary.ArticleInfo> findArticleList(int size, Long cursor, Article.ArticleType type);
    ArticleSummary.ArticleDetails findArticleDetails(Long articleId, Long memberId);
}
