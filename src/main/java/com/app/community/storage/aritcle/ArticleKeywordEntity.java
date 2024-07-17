package com.app.community.storage.aritcle;

import com.app.community.domain.aritcle.Article;
import com.app.community.domain.aritcle.Keyword;
import com.app.community.storage.AbstractEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "article_keywords")
@Entity
public class ArticleKeywordEntity extends AbstractEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long keywordId;
    private Long articleId;
    private String keywordName;

    @Builder
    public ArticleKeywordEntity(Long id, Long keywordId, Long articleId, String keywordName) {
        this.id = id;
        this.keywordId = keywordId;
        this.articleId = articleId;
        this.keywordName = keywordName;
    }

    public static ArticleKeywordEntity create(Long articleId, Long keywordId, String keywordName){
        return ArticleKeywordEntity.builder()
                .articleId(articleId)
                .keywordId(keywordId)
                .keywordName(keywordName)
                .build();
    }

    public static ArticleKeywordEntity fromDomain(Keyword keyword, Article article) {
        return ArticleKeywordEntity.builder()
                .articleId(article.getId())
                .keywordId(keyword.getId())
                .keywordName(keyword.getKeywordName())
                .build();
    }

    public Keyword toDomain(){
        return Keyword.builder()
                .id(id)
                .keywordName(keywordName)
                .build();
    }
}
