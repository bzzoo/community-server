package com.app.community.storage.db.command.aritcle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordJpaRepository extends JpaRepository<KeywordEntity, Long> {
    List<KeywordEntity> findByNameIn(List<String> keywordName);
}
