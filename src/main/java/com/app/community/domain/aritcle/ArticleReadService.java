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

    public CursorResult<ArticleSummary.ArticleInfo> getLatestArticleList(Long cursor, int size, Article.ArticleType type){
        List<ArticleSummary.ArticleInfo> articleList = articleRepositoryForQuery.findArticleList(size, cursor, type);
        return CursorResult.of(articleList, size, ArticleSummary.ArticleInfo::articleId);
    }
}
