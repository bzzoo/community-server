package com.app.community.storage.db.command.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatJpaRepository extends JpaRepository<ChatEntity, Long> {
    @Query(
            """ 
            SELECT c
            FROM ChatEntity c
            WHERE (c.requesterId = :requesterId AND c.respondentId = :respondentId)
            OR (c.requesterId = :respondentId AND c.respondentId = :requesterId)
            """)
    Optional<ChatEntity> findChatByParticipant(@Param("requesterId") Long requesterId, @Param("respondentId") Long respondentId);
}
