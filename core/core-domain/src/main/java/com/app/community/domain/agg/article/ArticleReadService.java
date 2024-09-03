package com.app.community.domain.agg.article;

import com.app.community.domain.agg.article.ArticleQuery.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleReadService {

    private final ArticleRepositoryForQuery articleRepositoryForQuery;

    public ArticleDetails getArticleDetails(Long articleId, Long memberId){
        return articleRepositoryForQuery.findArticleDetails(articleId, memberId);
    }

    public List<ArticleInfo> getLatestArticleList(int size, Long cursor, ArticleType type){
        return articleRepositoryForQuery.findArticleList(size, cursor, type);
    }

    public List<ArticleActivity> getArticleListByMemberId(int size, Long cursor, ArticleType type, Long memberId) {
        return articleRepositoryForQuery.findArticleListByMemberId(size, cursor, type, memberId);
    }
}
