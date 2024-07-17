package com.app.community.storage.aritcle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleKeywordJpaRepository extends JpaRepository<ArticleKeywordEntity, Long> {

    void deleteByArticleId(Long id);
    List<ArticleKeywordEntity> findAllByArticleId(Long articleId);
}
