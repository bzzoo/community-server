package com.app.community.domain.query;

import com.app.community.domain.model.article.ArticleSimpleCacheUpdater;
import com.app.community.domain.model.article.ArticleType;
import com.app.community.domain.query.ArticleQuery.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleQueryService {

    private final ArticleQueryRepository articleQueryRepository;
    private final ArticleSimpleCacheUpdater simpleCacheUpdater;

    public ArticleDetails getArticleDetails(Long articleId, Long memberId){
        simpleCacheUpdater.increaseViewCnt(articleId);
        return articleQueryRepository.findArticleDetails(articleId, memberId);
    }

    public List<ArticleSummary> getLatestArticleList(Integer size, Long cursor, ArticleType type){
        return articleQueryRepository.findArticleList(size, cursor, type);
    }

    public List<ArticleActivity> getArticleListByMemberId(Integer size, Long cursor, ArticleType type, Long memberId) {
        return articleQueryRepository.findArticleListByMemberId(size, cursor, type, memberId);
    }
}
