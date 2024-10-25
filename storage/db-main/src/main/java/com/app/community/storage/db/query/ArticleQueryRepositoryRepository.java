package com.app.community.storage.db.query;

import com.app.community.domain.query.ArticleQuery.ArticleActivity;
import com.app.community.domain.query.ArticleQuery.ArticleDetails;
import com.app.community.domain.query.ArticleQuery.ArticleSummary;
import com.app.community.domain.model.article.ArticleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ArticleQueryRepositoryRepository implements com.app.community.domain.query.ArticleQueryRepository {

    private final ArticleMapper articleMapper;

    @Override
    public List<ArticleSummary> findArticleList(int size, Long cursor, ArticleType type) {
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
