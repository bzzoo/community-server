package com.app.community.domain.model.article;

import java.util.List;

public interface KeywordRepository {
    List<Keyword> findByKeywordsIn(List<KeywordName> keywordNames);
    List<Keyword> saveAll(List<KeywordName> keywordNames);
}
