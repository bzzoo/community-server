package com.app.community.storage.db.command.aritcle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleKeywordJpaRepository extends JpaRepository<ArticleKeywordEntity, Long> {

    List<ArticleKeywordEntity> findByArticleId(Long articleId);
}
