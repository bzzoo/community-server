package com.app.community.domain.model.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class KeywordWriter {

    private final KeywordRepository keywordRepository;

    public List<Keyword> appendAll(List<KeywordName> newKeywordNames) {
        return keywordRepository.saveAll(newKeywordNames);
    }
}
