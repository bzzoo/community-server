package com.app.community.storage.db.command.aritcle;

import com.app.community.domain.model.article.Article;
import com.app.community.domain.model.article.ArticleContent;
import com.app.community.domain.model.article.ArticleKeywordList;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ArticleEntityConverter {

    public static ArticleEntity toEntity(Article article) {
        if (article == null) return null;

        return ArticleEntity.builder()
                .id(article.getId())
                .writerId(article.getWriterId())
                .title(article.getContent().title())
                .body(article.getContent().body())
                .type(article.getType())
                .status(article.getStatus())
                .build();
    }

    public static List<ArticleKeywordEntity> toKeywordEntities(ArticleKeywordList tags, Long articleId) {
        return tags.keywordIds().stream()
                .map(keywordId -> new ArticleKeywordEntity(null, articleId, keywordId))
                .toList();
    }

    public static Article toDomain(ArticleEntity articleEntity, List<ArticleKeywordEntity> articleKeywordEntities) {
        if (articleEntity == null) return null;

        ArticleContent content = new ArticleContent(articleEntity.getTitle(), articleEntity.getBody());

        Set<Long> ids = articleKeywordEntities.stream()
                .map(ArticleKeywordEntity::getId)
                .collect(Collectors.toSet());
        ArticleKeywordList articleKeywordList = new ArticleKeywordList(ids);

        return new Article(
                articleEntity.getId(),
                articleEntity.getWriterId(),
                content,
                articleEntity.getType(),
                articleEntity.getStatus(),
                articleKeywordList
        );
    }
}
