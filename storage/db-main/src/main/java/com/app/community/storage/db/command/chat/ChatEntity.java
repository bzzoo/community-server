package com.app.community.storage.db.command.chat;

import com.app.community.domain.model.chat.Chat;
import com.app.community.domain.model.chat.ChatDateTime;
import com.app.community.domain.model.chat.ChatParticipant;
import com.app.community.domain.model.chat.ChatStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chats")
@Entity
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "respondent_id")
    private Long respondentId;

    @Column(name = "requester_id")
    private Long requesterId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "end_at")
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
