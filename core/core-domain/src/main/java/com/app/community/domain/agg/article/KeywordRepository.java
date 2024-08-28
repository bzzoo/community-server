package com.app.community.domain.agg.article;

import java.util.List;

public interface KeywordRepository {
    List<Keyword> findByKeywordsIn(List<KeywordName> keywordNames);
    List<Keyword> saveAll(List<KeywordName> keywordNames);
}
