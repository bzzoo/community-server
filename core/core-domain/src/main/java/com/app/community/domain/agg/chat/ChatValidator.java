package com.app.community.domain.agg.chat;

import com.app.community.domain.agg.member.Member;
import com.app.community.domain.support.error.CoreApiException;
import com.app.community.domain.support.error.ErrorType;
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
        if(requesterId.equals(respondentId)) throw new CoreApiException(ErrorType.CHAT_SELF_REQUEST);
    }

    private void validateRespondentChatRefusal(Member respondent) {
        if(respondent.getProfile().memberSetting().chatRefusal())
            throw new CoreApiException(ErrorType.CHAT_OPPONENT_REFUSE);
    }
}
