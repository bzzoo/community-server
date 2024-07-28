package com.app.community.storage.comment;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

    boolean existsByArticleIdAndIsDeleteFalse(Long targetId);
}
