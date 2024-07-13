package com.app.community.domain.member;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    public void onboard(
            @NotNull Long memberId,
            @NotNull String nickname,
            @NotNull Member.Position position
    ) {
    }

    public void updateSettings(
        @NotNull Long memberId,
        @NotNull Boolean chatRefusal,
        @NotNull Integer chatPeePoint
    ){
    }

    public void getMemberInfo(@NotNull Long memberId){
    }
}
