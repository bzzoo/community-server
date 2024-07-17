package com.app.community.storage.aritcle;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {
}
