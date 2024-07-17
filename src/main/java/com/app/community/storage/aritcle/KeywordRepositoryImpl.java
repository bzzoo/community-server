package com.app.community.storage.aritcle;

import com.app.community.domain.aritcle.Keyword;
import com.app.community.domain.aritcle.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class KeywordRepositoryImpl implements KeywordRepository {
    @Override
    public List<Keyword> findByKeywordNameIn(List<String> keywordName) {
        return null;
    }

    @Override
    public List<Keyword> saveAll(List<Keyword> keywordList) {
        return null;
    }
}
