package com.app.community.storage.chat;

import com.app.community.domain.chat.Chat;
import com.app.community.storage.AbstractEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chats")
@Entity
public class ChatEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long respondentId;
    private Long requesterId;
    private Boolean isEnd;
    private LocalDateTime endDate;

    @Builder
    public ChatEntity(Long id, Long respondentId, Long requesterId, Boolean isEnd, LocalDateTime endDate) {
        this.id = id;
        this.respondentId = respondentId;
        this.requesterId = requesterId;
        this.isEnd = isEnd;
        this.endDate = endDate;
    }

    public static ChatEntity fromDomain(Chat chat){
        return ChatEntity.builder()
                .id(chat.getId())
                .respondentId(chat.getRespondentId())
                .requesterId(chat.getRequesterId())
                .isEnd(chat.getIsEnd())
                .endDate(chat.getEndDate())
                .build();

    }

    public Chat toDomain(){
        return Chat.builder()
                .id(this.getId())
                .respondentId(this.getRespondentId())
                .requesterId(this.getRequesterId())
                .isEnd(this.getIsEnd())
                .endDate(this.getEndDate())
                .build();
    }
}
