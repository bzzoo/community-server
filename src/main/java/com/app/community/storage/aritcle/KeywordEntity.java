package com.app.community.storage.aritcle;

import com.app.community.storage.AbstractEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "keywords")
@Entity
public class KeywordEntity extends AbstractEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String keywordName;

    @Builder
    private KeywordEntity(Long id, String keywordName) {
        this.id = id;
        this.keywordName = keywordName;
    }

    public static KeywordEntity create(String keywordName){
        return new KeywordEntity(null, keywordName);
    }
}
