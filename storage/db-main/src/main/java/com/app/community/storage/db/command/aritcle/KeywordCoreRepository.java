package com.app.community.storage.db.command.aritcle;

import com.app.community.domain.agg.article.Keyword;
import com.app.community.domain.agg.article.KeywordName;
import com.app.community.domain.agg.article.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class KeywordCoreRepository implements KeywordRepository {

    private final KeywordJpaRepository keywordJpaRepository;

    @Override
    public List<Keyword> findByKeywordsIn(List<KeywordName> keywordNames) {
        List<String> toString = keywordNames.stream()
                .map(KeywordName::value)
                .collect(Collectors.toList());

        return keywordJpaRepository.findByNameIn(toString).stream()
                .map(KeywordEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Keyword> saveAll(List<KeywordName> keywordNames) {
        List<KeywordEntity> keywordEntities = keywordNames.stream()
                .map(name -> new KeywordEntity(null, name.value()))
                .collect(Collectors.toList());

        return keywordJpaRepository.saveAll(keywordEntities).stream()
                .map(KeywordEntity::toDomain)
                .collect(Collectors.toList());
    }
}
