package com.app.community.storage.db.query;

import com.app.community.domain.agg.article.ArticleQuery.*;
import com.app.community.domain.agg.article.ArticleRepositoryForQuery;
import com.app.community.domain.agg.article.ArticleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ArticleQueryRepository implements ArticleRepositoryForQuery {

    private final ArticleMapper articleMapper;

    @Override
    public List<ArticleInfo> findArticleList(int size, Long cursor, ArticleType type) {
        return articleMapper.findArticleList(size, cursor, type);
    }

    @Override
    public ArticleDetails findArticleDetails(Long articleId, Long memberId) {
        return articleMapper.findArticleDetails(articleId);
    }

    @Override
    public List<ArticleActivity> findArticleListByMemberId(int size, Long cursor, ArticleType type, Long memberId) {
        return articleMapper.findArticleListByMemberId(size, cursor, type, memberId);
    }
}
