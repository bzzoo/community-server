package com.app.community.domain.model.chat;

import com.app.community.domain.model.member.Member;
import com.app.community.domain.model.member.MemberSetting;
import com.app.community.domain.support.error.DomainErrorType;
import com.app.community.domain.support.error.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatValidator {

    public void validateChatStatus() {

    }

    public void validateCheckout(Member requester, Member respondent, int amount, int period) {
        MemberSetting respondentStatus = respondent.getProfile().memberSetting();
        // 본인에게 요청하는 것은 불가능하다
        if (requester.getId().equals(respondent.getId()))
            throw new DomainException(DomainErrorType.DEFAULT_ERROR);
        // 상대방이 채팅 거절 상태이면 불가능하다.
        if (respondentStatus.chatRefusal())
            throw new DomainException(DomainErrorType.DEFAULT_ERROR);
        // 요청자가 상대방이 설정해 놓은 포인트보다 적은 포인틀르 갖고 있으면 불가능하다.
        if (requester.getGrade().value() < respondentStatus.chatPeePoint())
            throw new DomainException(DomainErrorType.DEFAULT_ERROR);
        //금액 또는 기간이 응답자와 설정해놓은 것과 다르면 불가능하다.
        if (respondentStatus.chatPeePoint() != amount)
            throw new DomainException(DomainErrorType.DEFAULT_ERROR);
    }
}
