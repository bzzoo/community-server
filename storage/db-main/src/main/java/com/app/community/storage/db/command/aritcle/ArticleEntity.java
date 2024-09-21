package com.app.community.storage.db.command.aritcle;

import com.app.community.domain.agg.article.ArticleStatus;
import com.app.community.domain.agg.article.ArticleType;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "writer_id")
    private Long writerId;

    @Column(name = "title")
    private String title;

    @Lob @Column(name = "body")
    private String body;

    @Column(name ="type")
    @Enumerated(EnumType.STRING)
    private ArticleType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

}
