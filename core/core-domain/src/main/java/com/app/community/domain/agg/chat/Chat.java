package com.app.community.domain.agg.chat;

import com.app.community.domain.support.error.DomainException;
import com.app.community.domain.support.error.DomainErrorType;
import lombok.Getter;

@Getter
public class Chat {
    private Long id;
    private ChatParticipant participant;
    private ChatDateTime chatDateTime;
    private ChatStatus status;

    public Chat(
            Long id,
            ChatParticipant participant,
            ChatDateTime chatDateTime,
            ChatStatus status
    ) {
        this.id = id;
        this.participant = participant;
        this.status = status;
        this.chatDateTime = chatDateTime;
    }

    public static Chat create(
            ChatParticipant participant,
            ChatDateTime chatDateTime
    ) {
        return new Chat(null, participant, chatDateTime, ChatStatus.PROGRESS);
    }

    public void validateDate(){
        if(this.getStatus().equals(ChatStatus.END)) throw new DomainException(DomainErrorType.CHAT_ENDED);
    }
}
