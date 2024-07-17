package com.app.community.domain.aritcle;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class KeywordGenerator {

    private final KeywordRepository keywordRepository;

    @Transactional
    public List<Keyword> saveIfNotExists(List<String> keywordNameList) {
        List<Keyword> existingKeywordList = keywordRepository.findByKeywordNameIn(keywordNameList);
        Set<String> existingKeywordNames = existingKeywordList.stream()
                .map(Keyword::getKeywordName)
                .collect(Collectors.toSet());

        List<Keyword> keywordsToSave = keywordNameList.stream()
                .filter(keywordName -> !existingKeywordNames.contains(keywordName))
                .map(Keyword::create)
                .collect(Collectors.toList());

        return keywordRepository.saveAll(keywordsToSave);
    }
}
