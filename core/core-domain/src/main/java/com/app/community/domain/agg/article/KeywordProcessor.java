package com.app.community.domain.agg.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class KeywordProcessor {

    private final KeywordReader keywordReader;
    private final KeywordWriter keywordWriter;

    public List<Keyword> process(List<KeywordName> keywordNames) {
        List<Keyword> existingKeywords = keywordReader.getKeywordsNames(keywordNames);

        Set<KeywordName> newKeywordNames = new HashSet<>(keywordNames);
        newKeywordNames.removeAll(existingKeywords.stream()
                .map(Keyword::getName)
                .collect(Collectors.toSet()));

        List<Keyword> newKeywords = keywordWriter.appendAll(new ArrayList<>(newKeywordNames));

        return Stream.concat(existingKeywords.stream(), newKeywords.stream())
                .collect(Collectors.toList());
    }
}
