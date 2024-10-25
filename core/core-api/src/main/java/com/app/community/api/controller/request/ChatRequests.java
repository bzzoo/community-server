package com.app.community.api.controller.request;

import com.app.community.domain.model.chat.ChatDateTime;
import com.app.community.domain.model.chat.ChatParticipant;

public class ChatRequests {

    public record CheckoutRequest(
            Long requesterId,
            Long respondentId,
            int amount,
            int period

    ){
        public ChatParticipant toParticipant(){
            return new ChatParticipant(requesterId, respondentId);
        }
        public ChatDateTime toDatetime(){ return ChatDateTime.ofPeriod(period); }
    }
}
