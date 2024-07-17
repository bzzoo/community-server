package com.app.community.storage.aritcle;

import com.app.community.domain.aritcle.Article;
import com.app.community.storage.AbstractEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "articles")
@Entity
public class ArticleEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long writerId;
    private String title;
    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private Article.ArticleStatus articleStatus;

    @Enumerated(EnumType.STRING)
    private Article.ArticleType articleType;

    @Builder
    public ArticleEntity(
            Long id,
            Long writerId,
            String title,
            String content,
            Article.ArticleStatus articleStatus,
            Article.ArticleType articleType
    ) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.articleStatus = articleStatus;
        this.articleType = articleType;
    }

    public static ArticleEntity fromDomain(Article article) {
        return ArticleEntity.builder()
                .id(article.getId())
                .writerId(article.getWriterId())
                .title(article.getTitle())
                .content(article.getContent())
                .articleStatus(article.getArticleStatus())
                .articleType(article.getArticleType())
                .build();
    }

    public Article toDomain() {
        return Article.builder()
                .id(id)
                .writerId(writerId)
                .title(title)
                .content(content)
                .articleStatus(articleStatus)
                .articleType(articleType)
                .build();
    }
}
