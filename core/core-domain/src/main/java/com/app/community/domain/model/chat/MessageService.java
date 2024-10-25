package com.app.community.domain.model.chat;

import com.app.community.domain.model.member.Member;
import com.app.community.domain.model.member.MemberReader;
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
//        chatValidator.validate(chat, respondent);
        messageAppender.save(message, chat, senderId);
    }
}
