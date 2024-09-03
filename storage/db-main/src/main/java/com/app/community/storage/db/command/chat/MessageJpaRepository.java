package com.app.community.storage.db.command.chat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageJpaRepository extends JpaRepository<MessageEntity, Long> {
}
