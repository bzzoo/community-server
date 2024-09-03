package com.app.community.domain.agg.chat;

import com.app.community.domain.agg.member.Member;
import com.app.community.domain.agg.member.MemberReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MemberReader memberReader;
    private final ChatReader chatReader;
    private final ChatValidator chatValidator;
    private final MessageAppender messageAppender;

    public void saveMessage(String message, Long chatId, Long senderId){
        Chat chat = chatReader.getById(chatId);
        Member respondent = memberReader.getById(chat.getParticipant().respondentId());
        chatValidator.validate(chat, respondent);
        messageAppender.save(message, chat, senderId);
    }
}
