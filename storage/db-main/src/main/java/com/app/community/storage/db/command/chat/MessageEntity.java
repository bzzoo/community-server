package com.app.community.storage.db.command.chat;

import com.app.community.domain.agg.chat.Message;
import com.app.community.domain.agg.chat.MessageType;
import com.app.community.storage.db.command.AbstractEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chat_messages")
@Entity
public class MessageEntity extends AbstractEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private Long senderId;
    private String content;
    private Boolean isRead;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Builder
    public MessageEntity(Long id, Long chatId, Long senderId, String content, Boolean isRead, MessageType messageType) {
        this.id = id;
        this.chatId = chatId;
        this.senderId = senderId;
        this.content = content;
        this.isRead = isRead;
        this.messageType = messageType;
    }

    public static MessageEntity fromDomain(Message message){
        return MessageEntity.builder()
                .id(message.getId())
                .chatId(message.getChatId())
                .senderId(message.getSenderId())
                .content(message.getContent())
                .isRead(message.getIsRead())
                .messageType(message.getMessageType())
                .build();
    }

    public Message toDomain() {
        return Message.builder()
                .id(id)
                .chatId(chatId)
                .senderId(senderId)
                .content(content)
                .isRead(isRead)
                .messageType(messageType)
                .build();
    }
}
