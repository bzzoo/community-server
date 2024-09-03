package com.app.community.controller.request;

import com.app.community.domain.agg.chat.ChatDateTime;
import com.app.community.domain.agg.chat.ChatParticipant;

import java.time.LocalDateTime;

public class ChatRequests {

    public record ChatRequest(
            Long requesterId,
            Long respondentId,
            Integer period,
            LocalDateTime createdAt
    ){
        public ChatParticipant toParticipant(){
            return new ChatParticipant(requesterId, respondentId);
        }
        public ChatDateTime toDatetime(){
            return new ChatDateTime(createdAt, createdAt.plusDays(period));
        }
    }
}
