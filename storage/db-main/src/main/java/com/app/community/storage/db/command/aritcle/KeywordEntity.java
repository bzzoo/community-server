package com.app.community.storage.db.command.aritcle;

import com.app.community.domain.agg.article.Keyword;
import com.app.community.domain.agg.article.KeywordName;
import com.app.community.storage.db.command.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "keywords")
@Entity
public class KeywordEntity extends AbstractEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public static KeywordEntity fromDomain(Keyword keyword){
        return KeywordEntity.builder()
                .name(keyword.getName().value())
                .build();
    }

    public Keyword toDomain(){
        return new Keyword(id, new KeywordName(name));
    }
}
