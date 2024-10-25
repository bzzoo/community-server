package com.app.community.storage.db.command.aritcle;

import com.app.community.domain.model.article.Article;
import com.app.community.domain.model.article.ArticleContent;
import com.app.community.domain.model.article.ArticleStatus;
import com.app.community.domain.model.article.ArticleType;
import com.app.community.storage.db.command.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "articles")
@Entity
public class ArticleEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "writer_id")
    private Long writerId;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "body")
    private String body;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ArticleType type;

    @Column(name = "view_cnt")
    private int viewCount;

    @Column(name = "comment_cnt")
    private int commentCount;

    @Column(name = "upvote_cnt")
    private int upvoteCount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @PrePersist
    public void prePersist() {
        this.viewCount = 0;
        this.commentCount = 0;
        this.upvoteCount = 0;
    }

    public Article toDomain() {
        return new Article(id, writerId, new ArticleContent(title, body), type, status, null);
    }
}
