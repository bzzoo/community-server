package com.app.community.storage.db.command.aritcle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE ArticleEntity a SET a.viewCount = a.viewCount + :increment WHERE a.id = :articleId")
    void incrementViewCount(Long articleId, int increment);

    @Modifying
    @Transactional
    @Query("UPDATE ArticleEntity a SET a.commentCount = a.commentCount + :increment WHERE a.id = :articleId")
    void incrementCommentCount(Long articleId, int increment);

    @Modifying
    @Transactional
    @Query("UPDATE ArticleEntity a SET a.upvoteCount = a.upvoteCount + :increment WHERE a.id = :articleId")
    void incrementUpvoteCount(Long articleId, int increment);
}
