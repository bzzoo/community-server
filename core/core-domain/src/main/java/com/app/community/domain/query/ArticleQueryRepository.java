package com.app.community.domain.query;

import com.app.community.domain.model.article.ArticleType;
import com.app.community.domain.query.ArticleQuery.*;

import java.util.List;

public interface ArticleQueryRepository {
    List<ArticleSummary> findArticleList(int size, Long cursor, ArticleType type);
    ArticleDetails findArticleDetails(Long articleId, Long memberId);
    List<ArticleActivity> findArticleListByMemberId(int size, Long cursor, ArticleType type, Long memberId);
}
