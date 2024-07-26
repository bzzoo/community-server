package com.app.community.domain.aritcle;

import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleReadService {

    private final ArticleRepositoryForQuery articleRepositoryForQuery;

    public ArticleSummary.ArticleDetails getArticleDetails(Long articleId, Long memberId){
        return articleRepositoryForQuery.findArticleDetails(articleId, memberId);
    }

    public CursorResult<ArticleSummary.ArticleInfo> getLatestArticleList(int size, Long cursor, Article.ArticleType type){
        List<ArticleSummary.ArticleInfo> articleList = articleRepositoryForQuery.findArticleList(size, cursor, type);
        return CursorResult.of(articleList, size, ArticleSummary.ArticleInfo::articleId);
    }

    public CursorResult<ArticleSummary.ArticleActivity> getArticleListByMemberId(int size, Long cursor, Article.ArticleType type, Long memberId) {
        List<ArticleSummary.ArticleActivity> articleList = articleRepositoryForQuery.findArticleListByMemberId(size, cursor, type, memberId);
        return CursorResult.of(articleList, size, ArticleSummary.ArticleActivity::articleId);
    }
}
