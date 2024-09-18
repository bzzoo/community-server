package com.app.community.storage.db.command.chat;

import com.app.community.domain.agg.chat.Chat;
import com.app.community.domain.agg.chat.ChatDateTime;
import com.app.community.domain.agg.chat.ChatParticipant;
import com.app.community.domain.agg.chat.ChatStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chats")
@Entity
public class ChatEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long respondentId;
    private Long requesterId;
    private LocalDateTime createdAt;
    private LocalDateTime endAt;

    @Enumerated(EnumType.STRING)
    private ChatStatus status;


    public Chat toDomain() {
        ChatParticipant chatParticipant = new ChatParticipant(requesterId, respondentId);
        ChatDateTime chatDateTime = new ChatDateTime(createdAt, endAt);
        return new Chat(
                id,
                chatParticipant,
                chatDateTime,
                status
        );
    }
}
