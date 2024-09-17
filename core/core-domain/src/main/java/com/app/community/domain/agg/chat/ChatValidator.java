package com.app.community.domain.agg.chat;

import com.app.community.domain.agg.member.Member;
import com.app.community.domain.support.error.DomainException;
import com.app.community.domain.support.error.DomainErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatValidator {

    public void validate(Chat chat, Member respondent){
        chat.validateDate();
        validateRespondentChatRefusal(respondent);
    }

    public void validateNotSelfRequest(Long respondentId, Long requesterId){
        if(requesterId.equals(respondentId)) throw new DomainException(DomainErrorType.CHAT_SELF_REQUEST);
    }

    private void validateRespondentChatRefusal(Member respondent) {
        if(respondent.getProfile().memberSetting().chatRefusal())
            throw new DomainException(DomainErrorType.CHAT_OPPONENT_REFUSE);
    }
}
