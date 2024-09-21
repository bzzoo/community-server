package com.app.community.storage.db.command.chat;

import com.app.community.domain.agg.chat.Message;
import com.app.community.domain.agg.chat.MessageType;
import com.app.community.storage.db.command.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chat_messages")
@Entity
public class MessageEntity extends AbstractEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "body")
    private String body;

    @Column(name = "is_read")
    private Boolean isRead;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    public static MessageEntity fromDomain(Message message){
        return MessageEntity.builder()
                .id(message.getId())
                .chatId(message.getChatId())
                .senderId(message.getSenderId())
                .body(message.getBody())
                .isRead(message.getIsRead())
                .messageType(message.getMessageType())
                .build();
    }

    public Message toDomain() {
        return Message.builder()
                .id(id)
                .chatId(chatId)
                .senderId(senderId)
                .body(body)
                .isRead(isRead)
                .messageType(messageType)
                .build();
    }
}
