package com.app.community.storage.db.query;

import com.app.community.domain.agg.article.ArticleQuery.*;
import com.app.community.domain.agg.article.ArticleType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {

    List<ArticleInfo> findArticleList(
            @Param("size") int size,
            @Param("cursor") Long cursor,
            @Param("type") ArticleType type
    );

    List<ArticleActivity> findArticleListByMemberId(
            @Param("size") int size,
            @Param("cursor") Long cursor,
            @Param("type") ArticleType type,
            @Param("memberId") Long memberId
    );

    ArticleDetails findArticleDetails(@Param("articleId") Long articleId);
}