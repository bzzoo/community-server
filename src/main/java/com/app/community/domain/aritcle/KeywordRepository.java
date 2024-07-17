package com.app.community.domain.aritcle;

import java.util.List;

public interface KeywordRepository {
    List<Keyword> findByKeywordNameIn(List<String> keywordName);
    List<Keyword> saveAll(List<Keyword> keywordList);
}
