package com.app.community.storage.aritcle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordJpaRepository extends JpaRepository<KeywordEntity, Long> {
    List<KeywordEntity> findByKeywordNameIn(List<String> keywordName);
}
