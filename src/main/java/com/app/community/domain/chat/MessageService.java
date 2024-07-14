package com.app.community.domain.chat;

import com.app.community.domain.member.Member;
import com.app.community.domain.member.MemberReader;
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
        Member respondent = memberReader.getById(chat.getRespondentId());
        chatValidator.isChatAvailable(chat, respondent);
        messageAppender.append(message, chat, senderId);
    }
}
