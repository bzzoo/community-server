package com.app.community.storage.aritcle;

import com.app.community.domain.aritcle.Keyword;
import com.app.community.storage.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "keywords")
@Entity
public class KeywordEntity extends AbstractEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String keywordName;

    public static KeywordEntity fromDomain(Keyword keyword){
        return KeywordEntity.builder()
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
