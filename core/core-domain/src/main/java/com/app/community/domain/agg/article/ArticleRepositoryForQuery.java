package com.app.community.domain.agg.article;

import com.app.community.domain.agg.article.ArticleQuery.*;

import java.util.List;

public interface ArticleRepositoryForQuery {
    List<ArticleSummary> findArticleList(int size, Long cursor, ArticleType type);
    ArticleDetails findArticleDetails(Long articleId, Long memberId);
    List<ArticleActivity> findArticleListByMemberId(int size, Long cursor, ArticleType type, Long memberId);
}
