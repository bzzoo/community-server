package com.app.community.storage.db.command.aritcle;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {
}
