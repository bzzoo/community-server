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
    private Long writerId;
    private String title;
    @Lob
    private String content;
    @Enumerated(EnumType.STRING)
    private ArticleType type;
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

}
