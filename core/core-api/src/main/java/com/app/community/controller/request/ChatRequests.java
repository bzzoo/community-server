package com.app.community.controller.request;

import com.app.community.domain.agg.chat.ChatDateTime;
import com.app.community.domain.agg.chat.ChatParticipant;

public class ChatRequests {

    public record ChatRequest(
            Long requesterId,
            Long respondentId,
            Integer period
    ){
        public ChatParticipant toParticipant(){
            return new ChatParticipant(requesterId, respondentId);
        }
        public ChatDateTime toDatetime(){ return ChatDateTime.ofPeriod(period); }
    }
}
