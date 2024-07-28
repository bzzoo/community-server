package com.app.community.storage.aritcle;

import com.app.community.domain.aritcle.Keyword;
import com.app.community.domain.aritcle.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class KeywordRepositoryImpl implements KeywordRepository {

    private final KeywordJpaRepository keywordJpaRepository;

    @Override
    public List<Keyword> findByKeywordNameIn(List<String> keywordName) {
        return keywordJpaRepository.findByKeywordNameIn(keywordName).stream()
                .map(KeywordEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Keyword> saveAll(List<Keyword> keywordList) {
        List<KeywordEntity> keywordEntityList = keywordList.stream()
                .map(KeywordEntity::fromDomain)
                .collect(Collectors.toList());
        return keywordJpaRepository.saveAll(keywordEntityList).stream()
                .map(KeywordEntity::toDomain)
                .collect(Collectors.toList());
    }
}
