package com.app.community.domain.agg.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class KeywordReader {

    private final KeywordRepository keywordRepository;

    public List<Keyword> getKeywordsNames(List<KeywordName> keywordNames) {
        return keywordRepository.findByKeywordsIn(keywordNames);
    }
}
